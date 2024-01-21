package com.example.tae.controller;

import com.example.tae.entity.TradingStatement.TradingStatementDTO;
import com.example.tae.entity.TradingStatement.TradingStatementModalDTO;
import com.example.tae.service.BinService;
import com.example.tae.service.TradingStatementService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;


@Controller
@AllArgsConstructor
public class TradingStatementController {

    TradingStatementService tradingStatementService;

    @GetMapping("TradingStatement")
    public String TradingStatement(Model model) {
        System.out.println("-----------------거래명세서 페이지가 요청 되었습니다-----------------------");
        List<TradingStatementModalDTO> listTSD=tradingStatementService.showListOrderByorderCode();
        model.addAttribute("tSDList",listTSD);
        return "TradingStatement";
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
