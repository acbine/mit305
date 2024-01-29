package com.example.tae.service;

import com.example.tae.entity.DummyData.Product.ProjectPlanDTO;

import java.util.List;

public interface ProjectService {
    //품목명 불러오기
    List<String> productnameList();
    
    //생산계획불러오기
    List<ProjectPlanDTO> list();
    
    //생산계획등록
    void register (String projectName,  String setouputNum , String setProjectPlanDate);

    //생산계획삭제
    void delete(int code);
    //생산계획 수정
}
