package com.example.tae.service;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.entity.ReceivingProcessing.ReceivingProcessing;
import com.example.tae.entity.ReleaseProcess.ReleaseProcess;
import com.example.tae.entity.ReleaseProcess.dto.ReleaseDto;

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
public class ReleaseProcessServiceImpl implements ReleaseProcessService {

    private ReleaseRepository releaseRepository;
    private ReceivingProcessingRepository receivingProcessingRepository;
    private ProcurementPlanRepository procurementPlanRepository;
    private ProductInformationRepository productInformationRepository;


    @Override
    public ReleaseDto release(int release, int procurementPlan_code) {
        Optional<ProcurementPlan> procurementPlan = procurementPlanRepository.findById(procurementPlan_code);
        Optional<ProductInformationRegistration> productInformationRegistration = productInformationRepository.findById(procurementPlan_code);
        int store;
        Optional<ReleaseProcess> mostRecentShippingData = Optional.of(releaseRepository.findTop1ByOrderByModDateDesc(procurementPlan_code).orElseGet(
                () -> {
                    ReleaseProcess releaseProcess = ReleaseProcess.builder()
                            .releaseCNT(0)
                            .procurementPlan(procurementPlan.get())
                            .build();
                    releaseRepository.save(releaseProcess);
                    return releaseProcess;
                }
        ));
        Optional<ReceivingProcessing> mostRecentStoreData = Optional.of(receivingProcessingRepository.findTop1ByOrderByModDateDesc(procurementPlan_code).orElseGet(
                () -> {
                    return  ReceivingProcessing.builder()
                            .store(0)
                            .procurementPlan(procurementPlan.get())
                            .build();
                }
        ));

        store = mostRecentStoreData.get().getStore();

        ReleaseProcess releaseP = ReleaseProcess.builder()
                .procurementPlan(procurementPlan.get())
                .releaseCNT(release)
                .build();
        releaseRepository.save(releaseP);

        return new ReleaseDto().releaseProcessDTO(
                mostRecentShippingData.get(),
                productInformationRegistration.get(),
                productInformationRegistration.get().getProduct_name(),
                procurementPlan.get().getContract().getProduct_price(),
                store,
                procurementPlan_code
        );
    }


    @Override
    public List<ReleaseDto> getStockDeliver() {
        List<ProcurementPlan> procurementPlanList = procurementPlanRepository.findAllByProjectPlan_Id(1);
        List<ReleaseDto> releaseDtoList = new ArrayList<>();
        ReleaseDto releaseDto = new ReleaseDto();
        for (ProcurementPlan procurementPlan : procurementPlanList) {
            int planId = procurementPlan.getProcurementplan_code();
            Optional<ReleaseProcess> mostRecentShippingData = Optional.of(releaseRepository.findTop1ByOrderByModDateDesc(planId).orElseGet(
                    () -> {
                        ReleaseProcess releaseProcess = ReleaseProcess.builder()
                                .releaseCNT(0)
                                .procurementPlan(procurementPlan)
                                .build();
                        releaseRepository.save(releaseProcess);
                        return releaseProcess;
                    }
            ));
            Optional<ReceivingProcessing> receivingProcessing = Optional.of(receivingProcessingRepository.findTop1ByOrderByModDateDesc(planId)
                    .orElseGet(() -> {
                        return   ReceivingProcessing.builder()
                                .store(0)
                                .procurementPlan(procurementPlan)
                                .build();
                    }));
            int store = receivingProcessing.get().getStore();
            Optional<ProductInformationRegistration> productInformationRegistration = productInformationRepository.findById(planId);
            ReleaseDto releaseDto1 = releaseDto.releaseProcessDTO(
                    mostRecentShippingData.get(),
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

        if (state == 0) {/*품목 이름 검색*/
            List<ProductInformationRegistration> productInformationRegistrationList = productInformationRepository.findByProductInformationName(constraints);
            releaseDtoList = changeReleaseDataToReleaseDTOFormat(releaseDtoList, releaseDto, procurementPlanList, productInformationRegistrationList);
            return releaseDtoList;
        } else if (state == 1) {/*품목 코드 검색*/
            List<ProductInformationRegistration> productInformationRegistrationList = productInformationRepository.findByProductInformationCode(constraints);
            releaseDtoList = changeReleaseDataToReleaseDTOFormat(releaseDtoList, releaseDto, procurementPlanList, productInformationRegistrationList);
            return releaseDtoList;
        }
        return null;
    }

    public List<ReleaseDto> changeReleaseDataToReleaseDTOFormat(List<ReleaseDto> releaseDtoList, ReleaseDto releaseDto, List<ProcurementPlan> procurementPlanList, List<ProductInformationRegistration> productInformationRegistrationList) {
        int store=0;
        for (ProcurementPlan procurementPlan : procurementPlanList) {
            int planId = procurementPlan.getProcurementplan_code();
            Optional<ReleaseProcess> mostRecentShippingData = releaseRepository.findTop1ByOrderByModDateDesc(planId);
            Optional<ReceivingProcessing> receivingProcessing = receivingProcessingRepository.findTop1ByOrderByModDateDesc(planId);
            for(ProductInformationRegistration productInformationRegistration : productInformationRegistrationList) {
                if(productInformationRegistration.getProduct_code()==planId) {
                    if(receivingProcessing.isPresent()) {
                        store=receivingProcessing.get().getStore();
                    } else {
                        store = 0;
                    }

                    ReleaseDto releaseDto1 = releaseDto.releaseProcessDTO(
                            mostRecentShippingData.get(),
                            productInformationRegistration,
                            productInformationRegistration.getProduct_name(),
                            procurementPlan.getContract().
                                    getProduct_price(),
                            store,
                            planId
                    );
                    releaseDtoList.add(releaseDto1);
                }
            }
        }
        return releaseDtoList;
    }
}
