package com.example.tae.controller;

import com.example.tae.entity.ReleaseProcess.ReleaseProcess;
import com.example.tae.repository.ReleaseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.text.DateFormatter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    @ResponseBody
    public ResponseEntity<?> existence(@RequestParam("date1") String stringDate1, @RequestParam("date2") String stringDate2, @RequestParam("product") String product) throws ParseException {
        System.out.println("받아오는 데이터 정보 확인하기"+stringDate1+stringDate1+product);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime date1 = LocalDateTime.parse(stringDate1, format);
        LocalDateTime date2 = LocalDateTime.parse(stringDate2, format);
        List<ReleaseProcess> releaseProcessList = releaseRepository.findByReleaseProcessWithRegDate(date1, date2);
        releaseProcessList.forEach(System.out::println);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("releaseProcessList","일단 작동부터"));
    }




}
