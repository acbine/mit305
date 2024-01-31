package com.example.tae.entity.Contract.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
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
    private Date start_date;

    private Date contract_date;

}
