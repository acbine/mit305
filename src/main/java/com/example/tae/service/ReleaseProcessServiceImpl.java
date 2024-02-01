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
    public ReleaseDto release(int release, int product_code) {

        Optional<ProductInformationRegistration> productInformationRegistration = Optional.of(productInformationRepository.findById(product_code).orElseThrow(
                () -> new IllegalArgumentException("출고 오류 : 존재하지 않는 품목입니다")
        ));

        List<Contract> contracts = contractRepository.findByProductInformationRegistrationCode(productInformationRegistration.get());

        Optional<Existence> ex = Optional.of(existenceRepository.findByProductCode(productInformationRegistration.get()).orElseGet(
                () -> {
                    Existence e = Existence.builder()
                            .releaseCNT(0)
                            .productCode(productInformationRegistration.get())
                            .build();
                    return e;
                }
        ));
        existenceRepository.save(ex.get().updateRelease(release * -1));
        List<ProcurementPlan> procurementPlanList = procurementPlanRepository.findByProductCode(product_code);

        Existence existence = ex.get();

        for (ProcurementPlan procurementPlan : procurementPlanList) {
            ReleaseProcess releaseProcess = ReleaseProcess.builder()
                    .procurementPlan(procurementPlan)
                    .releaseCNT(release)
                    .build();
            releaseRepository.save(releaseProcess);
        }

        for(Contract contract : contracts) {
            return ReleaseDto.builder()
                    .productName(existence.getProductCode().getProduct_name())
                    .departureDate(existence.getModDate())
                    .texture(existence.getProductCode().getTexture())
                    .height(existence.getProductCode().getHeight())
                    .length(existence.getProductCode().getLength())
                    .weight(existence.getProductCode().getWeight())
                    .release(0)
                    .existence(existence.getReleaseCNT())
                    .existence_price(existence.getReleaseCNT() * contract.getProduct_price())
                    .build();
        }

        return null;
    }


    @Override
    public List<ReleaseDto> getStockDeliver() {
        List<ProductInformationRegistration> productInformationRegistrationList = productInformationRepository.findAll();
        List<ReleaseDto> releaseDtoList = new ArrayList<>();

        for (ProductInformationRegistration productInformationRegistration : productInformationRegistrationList) {
            List<Contract> contract = contractRepository.findByProductInformationRegistrationCode(productInformationRegistration);

            Optional<Existence> existence = Optional.of(existenceRepository.findByProductCode(productInformationRegistration).orElseGet(
                    () -> {
                        Existence e = Existence.builder()
                                .productCode(productInformationRegistration)
                                .releaseCNT(0)
                                .build();
                        existenceRepository.save(e);
                        return e;
                    }
            ));

            for (Contract contract1 : contract) {
                ReleaseDto releaseDto = ReleaseDto.builder()
                        .existence(existence.get().getReleaseCNT())
                        .height(existence.get().getProductCode().getHeight())
                        .weight(existence.get().getProductCode().getWeight())
                        .length(existence.get().getProductCode().getLength())
                        .productName(existence.get().getProductCode().getProduct_name())
                        .texture(existence.get().getProductCode().getTexture())
                        .existence_price(existence.get().getReleaseCNT() * contract1.getProduct_price())
                        .product_code(existence.get().getProductCode().getProduct_code())
                        .contract_pay(contract1.getProduct_price())
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
            productInformationRegistrationList.forEach(x -> log.info(x.toString()));
            releaseDtoList = changeReleaseDataToReleaseDTOFormat(releaseDtoList, releaseDto, procurementPlanList, productInformationRegistrationList);
            return releaseDtoList;
        }
        return null;
    }

    public List<ReleaseDto> changeReleaseDataToReleaseDTOFormat(List<ReleaseDto> releaseDtoList, ReleaseDto releaseDto, List<ProcurementPlan> procurementPlanList, List<ProductInformationRegistration> productInformationRegistrationList) {
        for (ProcurementPlan procurementPlan : procurementPlanList) {
            int planId = procurementPlan.getProcurementplan_code();
            Optional<ReleaseProcess> mostRecentShippingData = releaseRepository.findTop1ByOrderByModDateDesc(planId);
            for (ProductInformationRegistration productInformationRegistration : productInformationRegistrationList) {
                if (productInformationRegistration.getProduct_code() == planId) {
                    Optional<Existence> existence = Optional.of(existenceRepository.findByProductCode(productInformationRegistration).orElseGet(
                            () -> {
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
