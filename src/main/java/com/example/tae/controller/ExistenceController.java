package com.example.tae.controller;

import com.example.tae.entity.ReleaseProcess.ReleaseProcess;
import com.example.tae.repository.ReleaseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class ExistenceController {

    final ReleaseRepository releaseRepository;

    @GetMapping("existence")
    public String existence() {
        return "existence";
    }

    @GetMapping("existenceDate")
    public String existence(@RequestParam("date1") Date date1, @RequestParam("date2") Date date2) {
        List<ReleaseProcess> releaseProcessList = releaseRepository.findByReleaseProcessWithRegDate(date1, date2);

        return "existence";
    }




}
