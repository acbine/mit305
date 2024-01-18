package com.example.tae.controller;

import com.example.tae.entity.Contract.Contract;
import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.entity.ReceivingProcessing.ReceivingProcessing;
import com.example.tae.entity.ReleaseProcess.ReleaseProcess;
import com.example.tae.entity.ReleaseProcess.dto.ReleaseDto;
import com.example.tae.entity.dto.ExistenceDTO;
import com.example.tae.entity.dto.SearchDTO;
import com.example.tae.repository.ReceivingProcessingRepository;
import com.example.tae.repository.RegistrationRepository.ContractRepository;
import com.example.tae.repository.RegistrationRepository.ProcurementPlanRepository;
import com.example.tae.repository.RegistrationRepository.ProductInformationRepository;
import com.example.tae.repository.ReleaseRepository;
import com.example.tae.service.ReleaseProcessService;
import com.example.tae.service.ReleaseProcessServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@AllArgsConstructor
@Slf4j
public class StockController {
    private ReleaseProcessServiceImpl releaseProcessService;

    @GetMapping("stockDelivery")
    public String stockDelivery(Model model) {
        List<ReleaseDto> releaseDtoList = releaseProcessService.getStockDeliver();
        model.addAttribute("releaseInfo", releaseDtoList);
        return "stockDelivery";
    }

    @ResponseBody
    @GetMapping("searchStockDelivery")
    public ResponseEntity<?> stockDelivery(@RequestParam("state") int state, @RequestParam("constraints") String constraints) {
        List<ReleaseDto> releaseDtoList = releaseProcessService.getStockDeliver(state,constraints);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("releaseInfo", releaseDtoList));
    }

    @PostMapping("total/stockDelivery")
    @ResponseBody
    public ResponseEntity<?> release(@RequestBody ReleaseDto releaseDto) {
        int release = releaseDto.getRelease();
        ReleaseProcess releaseProcess = releaseProcessService.release(release);
        ExistenceDTO existenceDTO = new ExistenceDTO();
        int existence = releaseProcessService.existence(release);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("ReleaseProcess", releaseProcess,"existence", existence));
    }
}
