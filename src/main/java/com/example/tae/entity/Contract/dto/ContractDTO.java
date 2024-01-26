package com.example.tae.entity.Contract.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractDTO {

    private int contract_code;
    private int product_code;
    private String businessNumber;

    private String payment_method;
    private int product_price;
    private int lead_time;

    private Date contract_date;

}
