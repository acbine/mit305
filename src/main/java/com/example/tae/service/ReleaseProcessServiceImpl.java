package com.example.tae.service;

import com.example.tae.entity.Contract.Contract;
import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ProcurementPlan.dto.ProcurementPlanDto;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.entity.ReceivingProcessing.ReceivingProcessing;
import com.example.tae.entity.ReleaseProcess.Existence;
import com.example.tae.entity.ReleaseProcess.ReleaseProcess;
import com.example.tae.entity.ReleaseProcess.dto.ReleaseDto;

import com.example.tae.repository.ExistenceRepository;
import com.example.tae.repository.ReceivingProcessingRepository;
import com.example.tae.repository.RegistrationRepository.ContractRepository;
import com.example.tae.repository.RegistrationRepository.ProcurementPlanRepository;
import com.example.tae.repository.RegistrationRepository.ProductInformationRegistrationRepository;
import com.example.tae.repository.ReleaseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class ReleaseProcessServiceImpl implements ReleaseProcessService {

    private ReleaseRepository releaseRepository;
    private ProcurementPlanRepository procurementPlanRepository;
    private ProductInformationRegistrationRepository productInformationRepository;
    private ExistenceRepository existenceRepository;
    private ContractRepository contractRepository;


    @Override
    public ReleaseDto release(int release, int procurementPlan_code) {
        Optional<ProcurementPlan> procurementPlan = Optional.of(procurementPlanRepository.findById(procurementPlan_code).orElseThrow(
                () -> new IllegalArgumentException("해당 조달 계획이 존재하지 않습니다.")
        ));
        ProductInformationRegistration productInformationRegistration = procurementPlan.get().getContract().getProductInformationRegistration();

        Optional<Existence> ex = Optional.of(existenceRepository.findByProductCode(productInformationRegistration).orElseGet(
                ()-> {
                    Existence e = Existence.builder()
                            .releaseCNT(0)
                            .productCode(productInformationRegistration)
                            .build();
                    return e;
                }
        ));

        existenceRepository.save(ex.get().updateRelease(release*-1));
        ReleaseProcess releaseProcess = ReleaseProcess.builder()
                .procurementPlan(procurementPlan.get())
                .releaseCNT(release)
                .build();
        releaseRepository.save(releaseProcess);

        return ReleaseDto.builder()
                .productName(productInformationRegistration.getProduct_name())
                .product_code(procurementPlan_code)
                .departureDate(releaseProcess.getModDate())
                .texture(productInformationRegistration.getTexture())
                .height(productInformationRegistration.getHeight())
                .length(productInformationRegistration.getLength())
                .weight(productInformationRegistration.getWeight())
                .release(0)
                .existence(ex.get().getReleaseCNT())
                .existence_price(ex.get().getReleaseCNT()*procurementPlan.get().getContract().getProduct_price())
                .build();
    }


    @Override
    public List<ReleaseDto> getStockDeliver() {
       List<ProductInformationRegistration> productInformationRegistrationList = productInformationRepository.findAll();
       List<ReleaseDto> releaseDtoList = new ArrayList<>();

       for(ProductInformationRegistration productInformationRegistration : productInformationRegistrationList) {
           List<ProcurementPlan> procurementPlanList = contractRepository.findByproductInformationId(productInformationRegistration.getProduct_code());
           for( ProcurementPlan procurementPlan : procurementPlanList) {
               Optional<Existence> existence = Optional.of(existenceRepository.findByProductCode(productInformationRegistration).orElseGet(
                       ()-> {
                           Existence e = Existence.builder()
                                   .productCode(productInformationRegistration)
                                   .releaseCNT(0)
                                   .build();
                           existenceRepository.save(e);
                           return e;
                       }
               ));


               ReleaseDto releaseDto = ReleaseDto.builder()
                       .procurementPlan_code(procurementPlan.getProcurementplan_code())
                       .existence(existence.get().getReleaseCNT())
                       .height(productInformationRegistration.getHeight())
                       .weight(productInformationRegistration.getWeight())
                       .length(productInformationRegistration.getLength())
                       .productName(productInformationRegistration.getProduct_name())
                       .texture(productInformationRegistration.getTexture())
                       .existence_price(existence.get().getReleaseCNT()*procurementPlan.getContract().getProduct_price())
                       .product_code(productInformationRegistration.getProduct_code())
                       .contract_pay(procurementPlan.getContract().getProduct_price())
                       .build();
               releaseDtoList.add(releaseDto);
               }
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
