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

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public ReleaseDto release(int release,int procurementPlan_code) {
        Optional<ProcurementPlan> procurementPlan = procurementPlanRepository.findById(procurementPlan_code);
        Optional<ReceivingProcessing> receivingProcessing = receivingProcessingRepository.findTop1ByOrderByModDateDesc(procurementPlan_code);
        Optional<ProductInformationRegistration> productInformationRegistration = productInformationRepository.findById(procurementPlan_code);
        ReleaseProcess mostRecentShippingData = releaseRepository.findTop1ByOrderByModDateDesc();
        int store = receivingProcessing.get().getStore();

        log.info("서비스단으로 넘어온 정보확인 : "+ release);
        ReleaseProcess releaseP = ReleaseProcess.builder()
                .procurementPlan(procurementPlan.get())
                .releaseCNT(release)
                .build();
        releaseRepository.save(releaseP);

        log.info("만들어진 재고값 보기 : "+releaseP);
        return new ReleaseDto().releaseProcessDTO(
                mostRecentShippingData,
                productInformationRegistration.get(),
                productInformationRegistration.get().getProduct_name(),
                procurementPlan.get().getContract().
                        getProduct_price(),
                store,
                procurementPlan_code
        );
    }

    @Override
    public int existence(int release, int procurementPlan_code) {

        Optional<ReceivingProcessing> receivingProcessing = receivingProcessingRepository.findTop1ByOrderByModDateDesc(procurementPlan_code);

        ExistenceDTO existenceDTO = new ExistenceDTO();
        return receivingProcessing.map(processing -> existenceDTO.existence(release, processing)).orElse(release);
    }

    @Override
    public List<ReleaseDto> getStockDeliver() {
        List<ProcurementPlan> procurementPlanList = procurementPlanRepository.findAllByProjectPlan_Id(1);
        List<ReleaseDto> releaseDtoList = new ArrayList<>();
        ReleaseProcess mostRecentShippingData = releaseRepository.findTop1ByOrderByModDateDesc();
        ReleaseDto releaseDto = new ReleaseDto();


        for (ProcurementPlan procurementPlan : procurementPlanList) {
            int planId = procurementPlan.getProcurementplan_code();
            Optional<ReceivingProcessing> receivingProcessing = receivingProcessingRepository.findTop1ByOrderByModDateDesc(planId);
            int store = receivingProcessing.get().getStore();
            Optional<ProductInformationRegistration> productInformationRegistration = productInformationRepository.findById(planId);
            ReleaseDto releaseDto1 = releaseDto.releaseProcessDTO(
                    mostRecentShippingData,
                    productInformationRegistration.get(),
                    productInformationRegistration.get().getProduct_name(),
                    procurementPlan.getContract().
                            getProduct_price(),
                    store,
                    planId
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

        if (state == 0) {
            for (ProcurementPlan procurementPlan : procurementPlanList) {
                List<ProductInformationRegistration> productInformationRegistrationList = productInformationRepository.findByProductInformationName(constraints);
                productInformationRegistrationList.forEach(productInformationRegistration -> {
                    int planId = procurementPlan.getProcurementplan_code();
                    Optional<ReceivingProcessing> receivingProcessing = receivingProcessingRepository.findTop1ByOrderByModDateDesc(planId);
                    int store = receivingProcessing.get().getStore();
                    ReleaseDto releaseDto1 = releaseDto.releaseProcessDTO(
                            mostRecentShippingData,
                            productInformationRegistration,
                            productInformationRegistration.getProduct_name(),
                            procurementPlan.getContract().
                                    getProduct_price(),
                            store,
                            planId
                    );
                    releaseDtoList.add(releaseDto1);
                });

                return releaseDtoList;
            }
        } else if (state == 1) {
            for (ProcurementPlan procurementPlan : procurementPlanList) {
                List<ProductInformationRegistration> productInformationRegistrationList = productInformationRepository.findByProductInformationCode(constraints);
                productInformationRegistrationList.forEach(productInformationRegistration -> {
                    int planId = procurementPlan.getProcurementplan_code();
                    Optional<ReceivingProcessing> receivingProcessing = receivingProcessingRepository.findTop1ByOrderByModDateDesc(planId);
                    int store = receivingProcessing.get().getStore();
                    ReleaseDto releaseDto1 = releaseDto.releaseProcessDTO(
                            mostRecentShippingData,
                            productInformationRegistration,
                            productInformationRegistration.getProduct_name(),
                            procurementPlan.getContract().
                                    getProduct_price(),
                            store,
                            planId
                    );
                    releaseDtoList.add(releaseDto1);
                });

                return releaseDtoList;
            }
        }
       return null;
    }



}
