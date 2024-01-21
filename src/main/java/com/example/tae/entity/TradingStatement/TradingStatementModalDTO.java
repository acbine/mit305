package com.example.tae.entity.TradingStatement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TradingStatementModalDTO {
    String orderCode; //발주서 코드

    String prouctName; //품명

    Integer count; //수량

    Integer price; //단가

    Integer pc; //공급가액

    String businessNumber;
    String departName;
    String businessName;
    String businessEmail;
    String fax;
    String businessTel;
}
