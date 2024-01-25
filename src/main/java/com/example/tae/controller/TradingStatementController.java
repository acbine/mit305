package com.example.tae.controller;

import com.example.tae.entity.TradingStatement.TradingStatementModalDTO;
import com.example.tae.service.TradingStatementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


@Controller
@AllArgsConstructor
public class TradingStatementController {

    TradingStatementService tradingStatementService;

    @GetMapping("TradingStatement")
    public String TradingStatement(Model model) {
//        System.out.println("-----------------거래명세서 페이지가 요청 되었습니다-----------------------");
//        List<TradingStatementModalDTO> listTSD=tradingStatementService.showListOrderByorderCode();
//        model.addAttribute("tSDList",listTSD);
        return "TradingStatement";
    }

    @GetMapping("TSSearch")
    public ResponseEntity<?> TSSearch(@RequestParam("inputData") String inputData, @RequestParam("searchData") String searchData , @RequestParam("state") String state) {
        System.out.println("거래명세서의 검색요청 들어옴");
        System.out.println("=======> 검색내용"+inputData +"========>검색종류"+searchData +"페이지 상태"+state);
        List <TradingStatementModalDTO> returnDTOList = tradingStatementService.search(inputData,Integer.parseInt(state));
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("tsDTO", returnDTOList));
    }


    @GetMapping("TradingStatementModal")
    public String TradingStatementModal(@RequestParam("uuid") String UUID,Model model) {
        System.out.println("모달창+++++++++++++++++++++++++");
        System.out.println(UUID);
        List<TradingStatementModalDTO>aa=tradingStatementService.Listppuuid(UUID);
        if(aa.size()<17){
            TradingStatementModalDTO dto= TradingStatementModalDTO.builder().build();
            while (aa.size()<17){
                aa.add(dto);
            }
        }
        System.out.println(aa.size());
        model.addAttribute("listModalDto",aa);
        model.addAttribute("onlyOne",aa.get(0));
        return "TradingStatementModal";
    }

}
