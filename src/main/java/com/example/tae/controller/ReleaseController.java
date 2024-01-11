package com.example.tae.controller;

import com.example.tae.entity.ReleaseProcess.ReleaseProcess;
import com.example.tae.service.ReleaseProcessServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ReleaseController {
    private ReleaseProcessServiceImpl releaseProcessService;

    @GetMapping("existence")
    public String existence() {
        return "existence";
    }

    @PostMapping("/release")
    public String release(@RequestBody int release,Model model) {
        ReleaseProcess releaseProcess = releaseProcessService.release(release);
        model.addAttribute("release",releaseProcess);
        return "existence";
    }



}
