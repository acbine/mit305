package com.example.tae.service.PurchaseService;

import com.example.tae.entity.Order.ProgressInspection;
import com.example.tae.entity.Order.Purchase;
import com.example.tae.entity.Order.PurchaseRepository;
import com.example.tae.entity.Order.dto.OrderInspectDTO;
import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.Order.dto.ProgressInspectionDTO;
import com.example.tae.repository.ExistenceRepository;
import com.example.tae.repository.OrderRepository;
import com.example.tae.repository.ProgressInspectionRepository;
import com.example.tae.repository.RegistrationRepository.ContractRepository;
import com.example.tae.repository.RegistrationRepository.ProcurementPlanRepository;
import com.example.tae.repository.RegistrationRepository.ProductInformationRegistrationRepository;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProgressInspectorServiceImpl implements ProgressInspectorService{
    private final ProcurementPlanRepository procurementPlanRepository;

    private final ProgressInspectionRepository progressInspectionRepository;


    @Override
    /*진척 검수 등록 */
    public ProgressInspectionDTO orderInsepector(OrderInspectDTO inspection) {
        int planId = inspection.getPlanId();

        Optional<ProcurementPlan> procurementPlan = Optional.of(procurementPlanRepository.findById(planId).orElseThrow(
                ()-> new NullPointerException("해당 조달계획이 존재하지 않습니다.")));
        Purchase purchase = procurementPlan.get().getPurchase();
        Date date = inspection.getInspectDate();
        List<ProgressInspection> progressInspections = progressInspectionRepository.findByOrderCode(purchase);

        progressInspections.forEach(progressInspection -> {
            if(!progressInspection.isProgressInspectionStatus()) {
                throw new IllegalArgumentException("완료가 되지 않은 진척 검수가 있어 해당 진척 검수를 등록 할 수 없습니다. ");
            }
        });

        ProgressInspection progressInspection = ProgressInspection.builder()
                .progressInspectionPlan(date)
                .progressInspectionStatus(false)
                .orderCode(purchase)
                .build();
        progressInspectionRepository.save(progressInspection);

        return  ProgressInspectionDTO.makeDt(progressInspection,procurementPlan.get().getContract().getProductInformationRegistration().getProduct_name(),purchase.getModDate());
    }

    @Override
    public List<ProgressInspectionDTO> getProgressInspectorList(int planId) {
        List<ProgressInspectionDTO> progressInspectionDTOList = new ArrayList<>();

        Optional<ProcurementPlan> procurementPlan = Optional.of(procurementPlanRepository.findById(planId).orElseThrow(
                ()-> new NullPointerException("해당 조달계획이 존재하지 않습니다.")));
        Purchase purchase = procurementPlan.get().getPurchase();
        List<ProgressInspection> progressInspections = progressInspectionRepository.findAllByOrderCode(purchase);
        progressInspections.forEach(progressInspectionInfo -> {
            ProgressInspectionDTO progressInspectionDTO = ProgressInspectionDTO.builder()
                    .progressInspectionId(progressInspectionInfo.getProgressInspectionNum())
                    .progressInspectonDate(progressInspectionInfo.getProgressInspectionPlan())
                    .orderDate(progressInspectionInfo.getOrderCode().getModDate())
                    .productName(procurementPlan.get().getContract().getProductInformationRegistration().getProduct_name())
                    .build();
            progressInspectionDTOList.add(progressInspectionDTO);
        });
        return progressInspectionDTOList;
    }

    @Override
    public void upDateProgressInspector(int progressInspectionId, Date updateDate) {

        System.out.println("서비스 까지 넘어온 데이터 정보 확인하기 : "+ progressInspectionId+"                 :             "+updateDate.toString());
        List<ProgressInspection> progressInspections = progressInspectionRepository.findByProgressInspectionIdAndCheckToStatus(progressInspectionId);

        progressInspections.forEach(progressInspection -> {
            System.out.println("받아온 정보값들 확인해보기 : "+progressInspection.toString());
            if(!progressInspection.isProgressInspectionStatus()) {
                throw new IllegalArgumentException("완료가 되지 않은 진척 검수가 있어 해당 진척 검수를 수정 할 수 없습니다. ");
            }
        });

        Optional<ProgressInspection> progressInspectionOptional = Optional.of(progressInspectionRepository.findById(progressInspectionId).orElseThrow(
                ()->new IllegalArgumentException("해당 진척검수 일정이 존재하지 않습니다. 다시 확인하세요.")
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


}
