package com.example.tae.entity.Contract.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractPageDTO {

    private int contract_page_code;
    private int contract_contract_code;
    private String contract_name; // 계약서 이미지 이름

    private Date contract_write_date;
}
