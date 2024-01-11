package com.example.tae.service;

import com.example.tae.entity.dto.ImageDTO;
import org.springframework.web.bind.annotation.RequestBody;

public interface TradingStatementService {
    void imageUpload( @RequestBody ImageDTO imageDTO);
}
