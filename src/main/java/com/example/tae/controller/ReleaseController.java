package com.example.tae.controller;

import com.example.tae.entity.ReleaseProcess.ReleaseProcess;
import com.example.tae.entity.ReleaseProcess.dto.ReleaseDto;
import com.example.tae.service.ReleaseProcessServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
@Slf4j
public class ReleaseController {
    private ReleaseProcessServiceImpl releaseProcessService;

    @GetMapping("existence")
    public String existence() {
        return "existence";
    }

    @PostMapping("total/release")
    public String release(@RequestBody ReleaseDto releaseDto,Model model) {
        log.info("해당 release 메소드가 실행되는지 작동확인");
        log.info(releaseDto.getRelease()+"받는 값 확인");
        ReleaseProcess releaseProcess = releaseProcessService.release(releaseDto.getRelease());
        model.addAttribute("releaseProcess",releaseProcess);
        return "existence";
    }



}
