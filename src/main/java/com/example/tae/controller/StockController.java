package com.example.tae.controller;

import com.example.tae.entity.ReleaseProcess.dto.ReleaseDto;
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
       if(constraints.equals("")) {
           List<ReleaseDto> releaseDtoList = releaseProcessService.getStockDeliver();
           return ResponseEntity.status(HttpStatus.OK).body(Map.of("releaseInfo", releaseDtoList));
       }
        List<ReleaseDto> releaseDtoList = releaseProcessService.getStockDeliver(state,constraints);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("releaseInfo", releaseDtoList));
    }

    @PostMapping("toStockDelivery")
    @ResponseBody
    public ResponseEntity<?> release(@RequestBody ReleaseDto releaseDto) {
        int product_code = releaseDto.getProduct_code();
        int release = releaseDto.getRelease();
        System.out.println(release+"                 "+product_code);
        ReleaseDto releaseDto1 = releaseProcessService.release(release,product_code);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("ReleaseInfo", releaseDto1));
    }
}
