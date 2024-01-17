package com.example.tae.controller;

import com.example.tae.entity.Contract.Contract;
import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.entity.ReceivingProcessing.ReceivingProcessing;
import com.example.tae.entity.ReleaseProcess.ReleaseProcess;
import com.example.tae.entity.ReleaseProcess.dto.ReleaseDto;
import com.example.tae.entity.dto.ExistenceDTO;
import com.example.tae.repository.ReceivingProcessingRepository;
import com.example.tae.repository.RegistrationRepository.ContractRepository;
import com.example.tae.repository.RegistrationRepository.ProcurementPlanRepository;
import com.example.tae.repository.RegistrationRepository.ProductInformationRepository;
import com.example.tae.repository.ReleaseRepository;
import com.example.tae.service.ReleaseProcessService;
import com.example.tae.service.ReleaseProcessServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private ProcurementPlanRepository procurementPlanRepository;
    private ProductInformationRepository productInformationRepository;
    private ReleaseRepository releaseRepository;
    private ReceivingProcessingRepository receivingProcessingRepository;
    


    @GetMapping("stockDelivery")
    public String stockDelivery(Model model) {

        List<ProcurementPlan> procurementPlanList = procurementPlanRepository.findAllByProjectPlan_Id(1);
        List<ReleaseDto> releaseDtoList = new ArrayList<>();
        ReleaseProcess mostRecentShippingData = releaseRepository.findTop1ByOrderByModDateDesc();
        ReleaseDto releaseDto = new ReleaseDto();
        Optional<ReceivingProcessing> receivingProcessing = receivingProcessingRepository.findTop1ByOrderByModDateDesc();
        int store = receivingProcessing.get().getStore();

        for(ProcurementPlan procurementPlan : procurementPlanList) {
            int planId =  procurementPlan.getProcurementplan_code();
            Optional<ProductInformationRegistration> productInformationRegistration = productInformationRepository.findById(planId);
            log.info(procurementPlan.toString());
            ReleaseDto releaseDto1 = releaseDto.releaseProcessDTO(
                    mostRecentShippingData,
                    productInformationRegistration.get(),
                    productInformationRegistration.get().getProduct_name(), 
                    procurementPlan.getContract().
                            getProduct_price(),store
            );
            releaseDtoList.add(releaseDto1);
        }
        
        model.addAttribute("releaseInfo", releaseDtoList);
        
        log.info("해당 부분까지 작동했으면 이 메세지가 보임");
        return "stockDelivery";
    }

    @PostMapping("total/stockDelivery")
    @ResponseBody
    public ResponseEntity<?> release(@RequestBody ReleaseDto releaseDto) {
        log.info("해당 release 메소드가 실행되는지 작동확인");
        log.info(releaseDto.getRelease()+"받는 값 확인");

        int release = releaseDto.getRelease();

        ReleaseProcess releaseProcess = releaseProcessService.release(release);
        ExistenceDTO existenceDTO = new ExistenceDTO();
        int existence = releaseProcessService.existence(release);
        log.info("여기까지 실행 중인지 확인");
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("ReleaseProcess", releaseProcess,"existence", existence));
    }
}
