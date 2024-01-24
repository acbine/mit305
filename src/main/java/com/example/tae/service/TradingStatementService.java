package com.example.tae.service;

import com.example.tae.entity.TradingStatement.TradingStatementModalDTO;
import com.example.tae.entity.dto.ImageDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 *  거래명세서에 관한 서비스
 */
public interface TradingStatementService {

    //홈페이지 들어갔을때 발주서 목록 보여죽기
    List<TradingStatementModalDTO> showListOrderByorderCode();

    //uuid기반을입력하여 품목정보 목록 불러오기
    List<TradingStatementModalDTO> Listppuuid(String uuid);
    void imageUpload( @RequestBody ImageDTO imageDTO); //이미지를  전송시켜주는 서비스
}
