package com.example.tae.service;

import com.example.tae.entity.dto.ImageDTO;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *  거래명세서에 관한 서비스
 */
public interface TradingStatementService {

    void imageUpload( @RequestBody ImageDTO imageDTO);
}
