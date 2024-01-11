package com.example.tae.controller;

import com.example.tae.entity.dto.ImageDTO;
import com.example.tae.service.TradingStatementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TradingStatementRestController {
    private TradingStatementService binService;
    @PostMapping("imageURl")
    public ImageDTO imageUpload( @RequestBody ImageDTO imageDTO){
       return binService.imageUpload(imageDTO);
    }
}
