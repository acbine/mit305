package com.example.tae.controller;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.entity.ReceivingProcessing.ReceivingProcessing;
import com.example.tae.entity.ReleaseProcess.Existence;
import com.example.tae.entity.ReleaseProcess.ReleaseProcess;
import com.example.tae.entity.dto.ExistenceDTO;
import com.example.tae.repository.ExistenceRepository;
import com.example.tae.repository.ReceivingProcessingRepository;
import com.example.tae.repository.RegistrationRepository.ProcurementPlanRepository;

import com.example.tae.repository.RegistrationRepository.ProductInformationRegistrationRepository;
import com.example.tae.repository.ReleaseRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@AllArgsConstructor
@Slf4j
public class ExistenceController {

    private final ReleaseRepository releaseRepository;

    private final ProductInformationRegistrationRepository productInformationRepository;

    private final ExistenceRepository existenceRepository;

    @GetMapping("existence")
    public String existence() {
        return "existence";
    }

    @GetMapping("existenceDate")
    @ResponseBody
    public ResponseEntity<?> existence(@RequestParam("date1") String stringDate1, @RequestParam("date2") String stringDate2, @RequestParam("product") String product) throws Exception {

        List<ExistenceDTO> existenceDTOList = new ArrayList<>();

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime date1 = LocalDateTime.parse(stringDate1+ " 00:00", format);
        LocalDateTime date2 = LocalDateTime.parse(stringDate2+ " 00:00", format);


        if(product.equals("")) {
            List<ReleaseProcess> releaseProcessList= releaseRepository.findByReleaseProcessWithModDate(date1, date2);
            for(ReleaseProcess releaseProcess : releaseProcessList) {
                ProcurementPlan procurementPlan = releaseProcess.getProcurementPlan();
                ProductInformationRegistration productInformationRegistration = procurementPlan.getContract().getProductInformationRegistration();

                ExistenceDTO existence = new ExistenceDTO();
                Optional<Existence> existence1 = Optional.of(existenceRepository.findByProductCode(productInformationRegistration).orElseGet(
                        ()->{
                            Existence existence2 = Existence.builder()
                                    .productCode(productInformationRegistration)
                                    .releaseCNT(0)
                                    .build();
                            existenceRepository.save(existence2);
                            return existence2;
                        }
                ));

                ExistenceDTO existenceDTO = existence.existence(existence1.get(),releaseProcess,procurementPlan.getContract().getProductInformationRegistration(),procurementPlan.getContract().getProduct_price());
                existenceDTOList.add(existenceDTO);
            }
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("existenceList", existenceDTOList));
        } else {
            List<ReleaseProcess> releaseProcessCollect = releaseRepository.findByReleaseProcessWithDateAndProductName(product, date1, date2);
for(ReleaseProcess releaseProcess : releaseProcessCollect) {
    System.out.println(releaseProcess.getProcurementPlan().getContract().getProductInformationRegistration().getProduct_name());
}
            for(ReleaseProcess releaseProcess : releaseProcessCollect) {

                ProcurementPlan procurementPlan = releaseProcess.getProcurementPlan();
                ProductInformationRegistration productInformationRegistration = procurementPlan.getContract().getProductInformationRegistration();

                Optional<Existence> existence1 = Optional.of(existenceRepository.findByProductCode(productInformationRegistration).orElseGet(
                        ()->{
                            Existence existence2 = Existence.builder()
                                    .productCode(productInformationRegistration)
                                    .releaseCNT(0)
                                    .build();
                            existenceRepository.save(existence2);
                            return existence2;
                        }
                ));

                ExistenceDTO existenceDTO = new ExistenceDTO();
                ExistenceDTO existence = existenceDTO.existence(existence1.get(), releaseProcess,productInformationRegistration, procurementPlan.getContract().getProduct_price());
                existenceDTOList.add(existence);
            }
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("existenceList", existenceDTOList));
        }
    }
}
