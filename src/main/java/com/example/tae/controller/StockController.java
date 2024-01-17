package com.example.tae.controller;

import com.example.tae.entity.ReleaseProcess.ReleaseProcess;
import com.example.tae.entity.ReleaseProcess.dto.ReleaseDto;
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

@Controller
@AllArgsConstructor
@Slf4j
public class StockController {
    private ReleaseProcessServiceImpl releaseProcessService;
    @GetMapping("stockDelivery")
    public String stockDelivery() {
        return "stockDelivery";
    }

    @PostMapping("total/stockDelivery")
    @ResponseBody
    public ResponseEntity<ReleaseProcess> release(@RequestBody ReleaseDto releaseDto) {
        log.info("해당 release 메소드가 실행되는지 작동확인");
        log.info(releaseDto.getRelease()+"받는 값 확인");
        ReleaseProcess releaseProcess = releaseProcessService.release(releaseDto.getRelease());
        log.info("여기까지 실행 중인지 확인");
        return ResponseEntity.status(HttpStatus.OK).body(releaseProcess);
    }

}
