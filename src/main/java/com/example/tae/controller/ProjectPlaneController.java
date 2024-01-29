package com.example.tae.controller;

import com.example.tae.entity.DummyData.Product.Project;
import com.example.tae.entity.DummyData.Product.ProjectPlan;
import com.example.tae.entity.DummyData.Product.ProjectPlanDTO;
import com.example.tae.repository.ProjectRepository.ProjectRepository;
import com.example.tae.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class ProjectPlaneController {

    ProjectService projectService;

    @GetMapping("ProjectPlan")
    public void ProjectPlan(Model model){
        System.out.println("생산계획 페이지 요청");

        List<String> modelList = projectService.productnameList(); //품목명리스트
        model.addAttribute("projectnameList",modelList); // 품목명

        List<ProjectPlanDTO> projectplanList=projectService.list();
        model.addAttribute("projectplanList",projectplanList);//생산꼐획

        //
        
    }

    @PostMapping("ProjectPlanRegister")
    public String ProjectPlanRegister(String projectName,  String setouputNum , String setProjectPlanDate){
        System.out.println("생산계획 등록 서비스 요청");
        System.out.println(projectName+setouputNum+setProjectPlanDate);
        projectService.register(projectName,setouputNum,setProjectPlanDate);
        System.out.println("잘등록됨");
        return "redirect:ProjectPlan";
    }

    @PostMapping("ProjectPlanDelete")
    public String ProjectPlanDelete(int deleteProjectCode){
        System.out.println("생산계획 삭제 서비스 요청");
        System.out.println(deleteProjectCode+"삭제코드***********");
        projectService.delete(deleteProjectCode);
        System.out.println("삭제됨");
        return "redirect:ProjectPlan";
    }

    @PostMapping("ProjectPlanUpdate")
    public String ProjectPlanUpdate(int updateprojectCode ,int updateNumber ,String upDateDate ){
        System.out.println("생산계획 수정 서비스 요청");
        System.out.println(updateprojectCode+"수정코드***********"+updateNumber+"----------------------"+upDateDate);
        System.out.println("수정됨");
        return "redirect:ProjectPlan";
    }

}
