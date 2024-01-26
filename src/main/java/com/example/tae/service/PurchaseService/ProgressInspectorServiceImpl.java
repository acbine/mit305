package com.example.tae.service.PurchaseService;

import com.example.tae.entity.Order.ProgressInspection;
import com.example.tae.entity.Order.Purchase;
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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProgressInspectorServiceImpl implements ProgressInspectorService{
    private final OrderRepository orderRepository;
    private final ProcurementPlanRepository procurementPlanRepository;
    private final ExistenceRepository existenceRepository;
    private final ContractRepository contractRepository;
    private final ProductInformationRegistrationRepository productInformationRegistrationRepository;

    private final ProgressInspectionRepository progressInspectionRepository;

    @Override
    /*진척 검수 등록 */
    public ProgressInspectionDTO orderInsepector(OrderInspectDTO inspection) {
        int planId = inspection.getPlanId();

        Optional<ProcurementPlan> procurementPlan = Optional.of(procurementPlanRepository.findById(planId).orElseThrow(
                ()-> new NullPointerException("해당 조달계획이 존재하지 않습니다.")));
        Purchase purchase = procurementPlan.get().getPurchase();
        Date date = inspection.getInspectDate();
        ProgressInspection progressInspection = ProgressInspection.builder()
                .progressInspectionPlan(date)
                .progressInspectionStatus(false)
                .orderCode(purchase)
                .build();
//        progressInspectionRepository.save(progressInspection);

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


}
