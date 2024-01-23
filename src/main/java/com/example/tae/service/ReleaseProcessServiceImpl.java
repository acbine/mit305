package com.example.tae.service;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.entity.ReceivingProcessing.ReceivingProcessing;
import com.example.tae.entity.ReleaseProcess.Existence;
import com.example.tae.entity.ReleaseProcess.ReleaseProcess;
import com.example.tae.entity.ReleaseProcess.dto.ReleaseDto;

import com.example.tae.repository.ExistenceRepository;
import com.example.tae.repository.ReceivingProcessingRepository;
import com.example.tae.repository.RegistrationRepository.ProcurementPlanRepository;
import com.example.tae.repository.RegistrationRepository.ProductInformationRegistrationRepository;
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
    private ProcurementPlanRepository procurementPlanRepository;
    private ProductInformationRegistrationRepository productInformationRepository;
    private ExistenceRepository existenceRepository;


    @Override
    public ReleaseDto release(int release, int procurementPlan_code) {
        Optional<ProcurementPlan> procurementPlan = procurementPlanRepository.findById(procurementPlan_code);
        ProductInformationRegistration productInformationRegistration = procurementPlan.get().getContract().getProductInformationRegistration();
        Optional<Existence> existence = Optional.of(existenceRepository.findByProductCode(productInformationRegistration).orElseGet(
                ()->{
                    Existence existence1 = Existence.builder()
                            .productCode(procurementPlan.get().getContract().getProductInformationRegistration())
                            .releaseCNT(release*-1)
                            .build();
                    existenceRepository.save(existence1);
                    return existence1;
                }
        ));
        existence.get().updateRelease(release*-1);
        existenceRepository.save(existence.get());
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

        ReleaseProcess releaseP = ReleaseProcess.builder()
                .procurementPlan(procurementPlan.get())
                .releaseCNT(release)
                .build();
        releaseRepository.save(releaseP);

        existenceRepository.save(existence.get().updateRelease(mostRecentShippingData.get().getReleaseCNT()*-1));

        return new ReleaseDto().releaseProcessDTO(
                mostRecentShippingData.get(),
                productInformationRegistration,
                existence.get().getProductCode().getProduct_name(),
                procurementPlan.get().getContract().getProduct_price(),
                existence.get(),
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
            ProductInformationRegistration productInformationRegistration = procurementPlan.getContract().getProductInformationRegistration();

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
            Optional<Existence> existence = Optional.of(existenceRepository.findByProductCode(productInformationRegistration).orElseGet(
                    ()->{
                        Existence existence1 = Existence.builder()
                                .productCode(productInformationRegistration)
                                .releaseCNT(0)
                                .build();
                        existenceRepository.save(existence1);
                        return existence1;
                    }
            ));


            existenceRepository.save(existence.get().updateRelease(mostRecentShippingData.get().getReleaseCNT()*-1));

            ReleaseDto releaseDto1 = releaseDto.releaseProcessDTO(
                    mostRecentShippingData.get(),
                    productInformationRegistration,
                    productInformationRegistration.getProduct_name(),
                    procurementPlan.getContract().
                            getProduct_price(),
                    existence.get(),
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
            log.info("데이터 잘 받아졌는지 확인",constraints);
            List<ProductInformationRegistration> productInformationRegistrationList = productInformationRepository.findByProductInformationName(constraints);
            productInformationRegistrationList.forEach(x-> log.info(x.toString()));
            releaseDtoList = changeReleaseDataToReleaseDTOFormat(releaseDtoList, releaseDto, procurementPlanList, productInformationRegistrationList);
            return releaseDtoList;
        } else if (state == 1) {/*품목 코드 검색*/
            List<ProductInformationRegistration> productInformationRegistrationList = productInformationRepository.findByProductInformationCode(constraints);
            productInformationRegistrationList.forEach(x-> log.info(x.toString()));
            releaseDtoList = changeReleaseDataToReleaseDTOFormat(releaseDtoList, releaseDto, procurementPlanList, productInformationRegistrationList);
            return releaseDtoList;
        }
        return null;
    }

    public List<ReleaseDto> changeReleaseDataToReleaseDTOFormat(List<ReleaseDto> releaseDtoList, ReleaseDto releaseDto, List<ProcurementPlan> procurementPlanList, List<ProductInformationRegistration> productInformationRegistrationList) {
        for (ProcurementPlan procurementPlan : procurementPlanList) {
            int planId = procurementPlan.getProcurementplan_code();
            Optional<ReleaseProcess> mostRecentShippingData = releaseRepository.findTop1ByOrderByModDateDesc(planId);
            for(ProductInformationRegistration productInformationRegistration : productInformationRegistrationList) {
                if(productInformationRegistration.getProduct_code()==planId) {
                    Optional<Existence> existence = Optional.of(existenceRepository.findByProductCode(productInformationRegistration).orElseGet(
                            ()->{
                                Existence existence1 = Existence.builder()
                                        .productCode(productInformationRegistration)
                                        .releaseCNT(0)
                                        .build();
                                existenceRepository.save(existence1);
                                return existence1;
                            }
                    ));

                    ReleaseDto releaseDto1 = releaseDto.releaseProcessDTO(
                            mostRecentShippingData.get(),
                            productInformationRegistration,
                            productInformationRegistration.getProduct_name(),
                            procurementPlan.getContract().
                                    getProduct_price(),
                            existence.get(),
                            planId
                    );
                    releaseDtoList.add(releaseDto1);
                }
            }
        }
        return releaseDtoList;
    }
}
