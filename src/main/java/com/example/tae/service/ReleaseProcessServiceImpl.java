package com.example.tae.service;

import com.example.tae.entity.Order.Purchase;
import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.entity.ReceivingProcessing.ReceivingProcessing;
import com.example.tae.entity.ReleaseProcess.ReleaseProcess;
import com.example.tae.entity.ReleaseProcess.dto.ReleaseDto;
import com.example.tae.entity.dto.ExistenceDTO;
import com.example.tae.repository.ReceivingProcessingRepository;
import com.example.tae.repository.RegistrationRepository.ProcurementPlanRepository;
import com.example.tae.repository.RegistrationRepository.ProductInformationRepository;
import com.example.tae.repository.ReleaseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ReleaseProcessServiceImpl implements ReleaseProcessService{

    private ReleaseRepository releaseRepository;
    private ReceivingProcessingRepository receivingProcessingRepository;
    private ProcurementPlanRepository procurementPlanRepository;
    private ProductInformationRepository productInformationRepository;


    @Override
    public ReleaseProcess release(int release) {
        log.info("서비스단으로 넘어온 정보확인 : "+ release);
        ReleaseProcess releaseP = ReleaseProcess.builder()
                .releaseCNT(release)
                .build();
//        releaseRepository.save(releaseP);
        log.info("만들어진 재고값 보기 : "+releaseP);
        return releaseP;
    }

    @Override
    public int existence(int release) {

        Optional<ReceivingProcessing> receivingProcessing = receivingProcessingRepository.findTop1ByOrderByModDateDesc();

        ExistenceDTO existenceDTO = new ExistenceDTO();
        return receivingProcessing.map(processing -> existenceDTO.existence(release, processing)).orElse(release);
    }

    @Override
    public List<ReleaseDto> getStockDeliver() {
        List<ProcurementPlan> procurementPlanList = procurementPlanRepository.findAllByProjectPlan_Id(1);
        List<ReleaseDto> releaseDtoList = new ArrayList<>();
        ReleaseProcess mostRecentShippingData = releaseRepository.findTop1ByOrderByModDateDesc();
        ReleaseDto releaseDto = new ReleaseDto();
        Optional<ReceivingProcessing> receivingProcessing = receivingProcessingRepository.findTop1ByOrderByModDateDesc();
        int store = receivingProcessing.get().getStore();

        for (ProcurementPlan procurementPlan : procurementPlanList) {
            int planId = procurementPlan.getProcurementplan_code();
            Optional<ProductInformationRegistration> productInformationRegistration = productInformationRepository.findById(planId);
            ReleaseDto releaseDto1 = releaseDto.releaseProcessDTO(
                    mostRecentShippingData,
                    productInformationRegistration.get(),
                    productInformationRegistration.get().getProduct_name(),
                    procurementPlan.getContract().
                            getProduct_price(), store
            );
            releaseDtoList.add(releaseDto1);
        }
        return releaseDtoList;
    }

    @Override
    public List<ReleaseDto> getStockDeliver(int state, String constraints) {
        List<ProcurementPlan> procurementPlanList = procurementPlanRepository.findAllByProjectPlan_Id(1);
        ReleaseDto releaseDto = new ReleaseDto();
        List<ReleaseDto> releaseDtoList = new ArrayList<>();
        ReleaseProcess mostRecentShippingData = releaseRepository.findTop1ByOrderByModDateDesc();
        Optional<ReceivingProcessing> receivingProcessing = receivingProcessingRepository.findTop1ByOrderByModDateDesc();
        int store = receivingProcessing.get().getStore();

        if (state == 0) {
            for (ProcurementPlan procurementPlan : procurementPlanList) {
                List<ProductInformationRegistration> productInformationRegistrationList = productInformationRepository.findByProductInformationName(constraints);
                productInformationRegistrationList.forEach(productInformationRegistration -> {
                    ReleaseDto releaseDto1 = releaseDto.releaseProcessDTO(
                            mostRecentShippingData,
                            productInformationRegistration,
                            productInformationRegistration.getProduct_name(),
                            procurementPlan.getContract().
                                    getProduct_price(),
                            store
                    );
                    releaseDtoList.add(releaseDto1);
                });

                return releaseDtoList;
            }
        } else if (state == 1) {
            for (ProcurementPlan procurementPlan : procurementPlanList) {
                List<ProductInformationRegistration> productInformationRegistrationList = productInformationRepository.findByProductInformationCode(constraints);
                productInformationRegistrationList.forEach(productInformationRegistration -> {
                    ReleaseDto releaseDto1 = releaseDto.releaseProcessDTO(
                            mostRecentShippingData,
                            productInformationRegistration,
                            productInformationRegistration.getProduct_name(),
                            procurementPlan.getContract().
                                    getProduct_price(),
                            store
                    );
                    releaseDtoList.add(releaseDto1);
                });

                return releaseDtoList;
            }
        }
       return null;
    }



}
