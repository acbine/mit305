package com.example.tae.controller;

import com.example.tae.entity.dto.ImageDTO;
import com.example.tae.service.TradingStatementServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BinRestController {
    private TradingStatementServiceImpl binService;

    /*-------------------------------거래명세서----------------------------*/
    @PostMapping("imageURl")
    public void imageUpload(@RequestBody ImageDTO imageDTO){
       binService.imageUpload(imageDTO);
    }

}
