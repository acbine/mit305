package com.example.tae.service.PurchaseService;

import com.example.tae.entity.Order.ProgressInspection;
import com.example.tae.entity.Order.Purchase;
import com.example.tae.entity.Order.dto.OrderInspectDTO;
import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.Order.dto.ProgressInspectionDTO;
import com.example.tae.repository.PurchaseRepository.OrderRepository;
import com.example.tae.repository.ProgressInspectionRepository;
import com.example.tae.repository.RegistrationRepository.ProcurementPlanRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class ProgressInspectorServiceImpl implements ProgressInspectorService{
    private final ProcurementPlanRepository procurementPlanRepository;

    private final ProgressInspectionRepository progressInspectionRepository;
    private final OrderRepository orderRepository;


    @Override
    /*진척 검수 등록 */
    public ProgressInspectionDTO orderInsepector(OrderInspectDTO inspection) {
        int planId = inspection.getPlanId();

        Optional<ProcurementPlan> procurementPlan = Optional.of(procurementPlanRepository.findById(planId).orElseThrow(
                ()-> new NullPointerException("진척 검수 등록 오류 :  해당 조달계획이 존재하지 않습니다.")));
        Purchase purchase = procurementPlan.get().getPurchase();
        Date date = inspection.getInspectDate();
        List<ProgressInspection> progressInspections = progressInspectionRepository.findByOrderCode(purchase);
        progressInspections.forEach(progressInspection -> {
            if(!progressInspection.isProgressInspectionStatus()) {
                throw new IllegalArgumentException("진척 검수 등록 오류 : 완료가 되지 않은 진척 검수가 있어 해당 진척 검수를 등록 할 수 없습니다. ");
            }
        });

        ProgressInspection progressInspection = ProgressInspection.builder()
                .progressInspectionPlan(date)
                .progressInspectionStatus(false)
                .orderCode(purchase)
                .build();
        progressInspectionRepository.save(progressInspection);

        return  ProgressInspectionDTO.makeDt(progressInspection,procurementPlan.get().getContract().getProductInformationRegistration().getProduct_name(),purchase.getModDate(),procurementPlan.get().getOrder_state());
    }

    @Override
    public List<ProgressInspectionDTO> getProgressInspectorList(int planId) {
        List<ProgressInspectionDTO> progressInspectionDTOList = new ArrayList<>();

        Optional<ProcurementPlan> procurementPlan = Optional.of(procurementPlanRepository.findById(planId).orElseThrow(
                ()-> new NullPointerException("진척검수 리스트를 가져오는데 문제가 생겼습니다 : 해당 조달계획이 존재하지 않습니다.")));

        Purchase purchase = procurementPlan.get().getPurchase();
        List<ProgressInspection> progressInspections = progressInspectionRepository.findAllByOrderCode(purchase);
        progressInspections.forEach(progressInspectionInfo -> {
            ProgressInspectionDTO progressInspectionDTO = ProgressInspectionDTO.builder()
                    .orderState(procurementPlan.get().getOrder_state())
                    .progressInspectorResult(progressInspectionInfo.isProgressInspectionStatus())
                    .progressInspectionId(progressInspectionInfo.getProgressInspectionNum())
                    .progressInspectonDate(progressInspectionInfo.getProgressInspectionPlan())
                    .orderDate(progressInspectionInfo.getOrderCode().getModDate())
                    .productName(procurementPlan.get().getContract().getProductInformationRegistration().getProduct_name())
                    .note(progressInspectionInfo.getNote())
                    .build();
            progressInspectionDTOList.add(progressInspectionDTO);
        });
        return progressInspectionDTOList;
    }

    @Override
    public void upDateProgressInspector(int progressInspectionId, Date updateDate) {
        List<ProgressInspection> progressInspections = progressInspectionRepository.findByProgressInspectionIdAndCheckToStatus(progressInspectionId);

        progressInspections.forEach(progressInspection -> {
                            if(!progressInspection.isProgressInspectionStatus()) {
//                                throw new IllegalArgumentException("진적검수 업데이트 오류 : 실행하지 않은 진척검수가 있습니다. 다시 확인하세요.");
                            }
        });

        Optional<ProgressInspection> progressInspectionOptional = Optional.of(progressInspectionRepository.findById(progressInspectionId).orElseThrow(
                ()->new IllegalArgumentException("진적검수 업데이트 오류 : 해당 진척검수 일정이 존재하지 않습니다. 다시 확인하세요.")
        ));
        ProgressInspection progressInspection = progressInspectionOptional.get();
        ProgressInspection newProgressInspector = ProgressInspection.builder()
                .progressInspectionNum(progressInspection.getProgressInspectionNum())
                .progressInspectionPlan(updateDate)
                .orderCode(progressInspection.getOrderCode())
                .progressInspectionStatus(progressInspection.isProgressInspectionStatus())
                .build();
        progressInspectionRepository.save(newProgressInspector);
    }

    @Override
    public List<String> inspectorResult(int inspectorId, boolean progressInspectorResult, String note) {
        Optional<ProgressInspection> progressInspectionOptional =  Optional.of(progressInspectionRepository.findById(inspectorId).orElseThrow(
                () -> new NullPointerException("진척 검수 결과 확인 오류 : 해당 진척검수는 존재하지 않습니다. 요청을 다시 보내주세요.")
        ));
        ProcurementPlan procurementPlan = procurementPlanRepository.findByPurchase_OrderCode(progressInspectionOptional.get().getOrderCode().getOrderCode());

       ProgressInspection progressInspection = progressInspectionOptional.get();

        ProgressInspection resultProgressInspector = ProgressInspection.builder()
                .progressInspectionStatus(true) //검수 처리 유무 변경
                .progressInspectionPlan(progressInspection.getProgressInspectionPlan())
                .progressInspectionNum(progressInspection.getProgressInspectionNum())
                .orderCode(progressInspection.getOrderCode())
                .note(note)
                .build();
        progressInspectionRepository.save(resultProgressInspector);

       if(progressInspectorResult) {
           ProcurementPlan progressInspectorResultUpdate = ProcurementPlan.builder()
                   .procurementplan_code(procurementPlan.getProcurementplan_code())
                   .projectPlan(procurementPlan.getProjectPlan())
                   .SupportProductAmount(procurementPlan.getSupportProductAmount())
                   .order_state("검수처리완료") // 검수처리 결과만 업데이트
                   .contract(procurementPlan.getContract())
                   .project(procurementPlan.getProject())
                   .purchase(procurementPlan.getPurchase())
                   .order_date(procurementPlan.getOrder_date())
                   .build();
           procurementPlanRepository.save(progressInspectorResultUpdate);
           String[] result = {"진척검수 완료", note};
           return List.of(result);
       } else {
           String[] result = {"재검수 요망", note};
           return List.of(result);
       }
    }

    @Override
    public void deleteProgressInspector(int progressInspectorId) {
        progressInspectionRepository.deleteById(progressInspectorId);
    }

}
