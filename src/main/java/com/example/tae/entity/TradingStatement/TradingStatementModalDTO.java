package com.example.tae.entity.TradingStatement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TradingStatementModalDTO {
    String orderCode; //발주서 코드

    String prouctName; //입고품명

    Integer count; //입고수량

    Integer price; //품목의 계약 단가

    Integer pc; //공급가액= 입고수량 * 단가

    LocalDateTime Arrival; //입고처리된 날자

    String businessNumber; //사업자번호
    String departName;      //업체명
    String businessName;    //대표이름
    String businessEmail;   //업체이메일
    String fax;             //업체팩스
    String businessTel;     //업체 전화번호
}
