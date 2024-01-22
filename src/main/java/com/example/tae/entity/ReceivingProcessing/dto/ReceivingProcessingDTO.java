package com.example.tae.entity.ReceivingProcessing.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReceivingProcessingDTO {
    int procurementplan_code;// 조달계획코드
    
    int productcode; //품목코드

    String productname; //품목명

    String departName;  //업체명
    
    String businessName; //사업자이름

    String businessNumber; // 사업자번호

    LocalDateTime DateOfOrder; //발주서발행일

    Date OrderDate; //발주일

    LocalDateTime Arrival; //입고처리된 날자

    int SupportProductAmount; //조달계획수량

    int store; //입고수량

    String orderState; //품목상태



}


