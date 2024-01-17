package com.example.tae.controller;

import com.example.tae.entity.ReleaseProcess.ReleaseProcess;
import com.example.tae.entity.ReleaseProcess.dto.ReleaseDto;
import com.example.tae.entity.dto.ExistenceDTO;
import com.example.tae.entity.dto.StateDTO;
import com.example.tae.repository.ReceivingProcessingRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@Slf4j
public class StockController {
    private ReleaseProcessServiceImpl releaseProcessService;

    @GetMapping("stockDelivery")
    public String stockDelivery(Model model) {
            List<ReleaseProcess> releaseProcessList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                ReleaseProcess releaseProcess = ReleaseProcess.builder()
                        .id(i)
                        .releaseCNT(i)
                        .build();
                releaseProcessList.add(releaseProcess);
                model.addAttribute("releaseProcessList", releaseProcessList);
            }
//         else if(stateDTO==1){
//
//        } else if(stateDTO==2) {
//
//        }
        return "stockDelivery";
    }

    @GetMapping("searchStockDelivery")
    public String stockDelivery(@RequestBody StateDTO stateDTO, Model model) {
//        if(stateDTO==1){
//
//        } else if(stateDTO==2) {
//
//        }
        return "stockDelivery";
    }
    @PostMapping("total/stockDelivery")
    @ResponseBody
    public ResponseEntity<?> release(@RequestBody ReleaseDto releaseDto) {
        log.info("해당 release 메소드가 실행되는지 작동확인");
        log.info(releaseDto.getRelease()+"받는 값 확인");
        int release = releaseDto.getRelease();
        ReleaseProcess releaseProcess = releaseProcessService.release(release);
//        ExistenceDTO existenceDTO = new ExistenceDTO();
//
//        existenceDTO.existence(releaseProcess.getReleaseCNT(),)
        int existence = releaseProcessService.existence(release);
        log.info("여기까지 실행 중인지 확인");
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("reInfo",releaseProcess,"existence",existence));
    }

}
