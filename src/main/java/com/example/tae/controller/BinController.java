package com.example.tae.controller;

import com.example.tae.entity.ReceivingProcessing.dto.ReceivingProcessingDTO;
import com.example.tae.service.BinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BinController {
    @Autowired
    BinService binService;
    /*--------------------------------------------------입고처리-------------------------------*/
    @GetMapping("ReceivingProcess")
    public String ReceivingProcess(Model model) {
        System.out.println("입고처리 페이지여는 요청들어옴 발주전 태만 보여줘");
        List<ReceivingProcessingDTO> receivingProcessingDTOList = binService.procurementPlanList();
        //receivingProcessingDTOList.forEach(x-> System.out.println(x));
        model.addAttribute("procumentList",receivingProcessingDTOList);
        return "ReceivingProcess";
    }

    @GetMapping("ReceivingProcessSearch")
    public String ReceivingProcessSearch() {
        System.out.println("입고처리의 검색요청들어옴");

        return "ReceivingProcess";
    }
    
    
    @GetMapping("ReceivingProcessStore")
    public String ReceivingProcessStore (@RequestParam("procurementplan_code")String procurementplan_code, @RequestParam("store") String store){
        System.out.println("입고처리 form 형태의 입고처리요청들어옴");
        System.out.println("조달계획번호-------"+procurementplan_code+"입고수량"+store);
       // binService.ReceivingProcessStore(procurementplan_code,store);
        return "redirect:total";
    }
    /*------------------------ --------------------현황관리 -----------------------------------*/
    @GetMapping("StatusManagementReport")
    public String StatusManagementReport() {
        return "StatusManagementReport";
    }

}
