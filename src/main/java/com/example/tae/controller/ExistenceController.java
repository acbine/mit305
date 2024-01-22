package com.example.tae.controller;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.entity.ReceivingProcessing.ReceivingProcessing;
import com.example.tae.entity.ReleaseProcess.ReleaseProcess;
import com.example.tae.entity.dto.ExistenceDTO;
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
    private final ProcurementPlanRepository procurementPlanRepository;
    private final ReceivingProcessingRepository receivingProcessingRepository;

    @GetMapping("existence")
    public String existence() {
        return "existence";
    }

    @GetMapping("existenceDate")
    @ResponseBody
    public ResponseEntity<List<ExistenceDTO>> existence(@RequestParam("date1") String stringDate1, @RequestParam("date2") String stringDate2, @RequestParam("product") String product) throws Exception {
        System.out.println("받아오는 데이터 정보 확인하기"+stringDate1+stringDate1+product);

        List<ExistenceDTO> existenceDTOList = new ArrayList<>();

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime date1 = LocalDateTime.parse(stringDate1+ " 00:00", format);
        LocalDateTime date2 = LocalDateTime.parse(stringDate2+ " 00:00", format);

        log.info("데이터 변환 확인 : ", date1, date2);
        
        if(product.equals("")) {
            List<ReleaseProcess> releaseProcessList= releaseRepository.findByReleaseProcessWithModDate(date1, date2);
            for(ReleaseProcess releaseProcess : releaseProcessList) {
                ProcurementPlan procurementPlan = releaseProcess.getProcurementPlan();
                int planId = procurementPlan.getProcurementplan_code();
                ReceivingProcessing receivingProcessing = receivingProcessingRepository.findByProcumentPlanCode(planId);
                Optional<ProductInformationRegistration> productInformationRegistration = productInformationRepository.findById(planId);

                ExistenceDTO existence = new ExistenceDTO();
                ExistenceDTO existenceDTO = existence.existence(releaseProcess,productInformationRegistration.get(),procurementPlan.getContract().getProduct_price(),receivingProcessing.getStore());

                existenceDTOList.add(existenceDTO);
            }

            return ResponseEntity.status(HttpStatus.OK).body(existenceDTOList);
        } else {
            List<ReleaseProcess> releaseProcessCollect = new ArrayList<>();
            List<ProductInformationRegistration> productInformationRegistrations = productInformationRepository.findByProductInformationName(product);
            productInformationRegistrations.forEach(productInformationRegistration -> {
                releaseProcessCollect.addAll(releaseRepository.findByReleaseProcessWithDateAndProductCode(productInformationRegistration.getProduct_code(), date1, date2));
                for(ReleaseProcess releaseProcess : releaseProcessCollect) {
                    ProcurementPlan procurementPlan = releaseProcess.getProcurementPlan();
                    int planId = procurementPlan.getProcurementplan_code();
                    ReceivingProcessing receivingProcessing =receivingProcessingRepository.findByProcumentPlanCode(planId);
                    ExistenceDTO existenceDTO = new ExistenceDTO();
                    ExistenceDTO existence = existenceDTO.existence(releaseProcess,productInformationRegistration,procurementPlan.getContract().getProduct_price(), receivingProcessing.getStore());
                    existenceDTOList.add(existence);
                }
            });
            return ResponseEntity.status(HttpStatus.OK).body(existenceDTOList);
        }
    }
}
