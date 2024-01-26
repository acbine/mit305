package com.example.tae.entity.Contract;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ContractPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contract_page_code;

    @ManyToOne
    Contract contract; // 계약 코드

    private String contract_name; // 계약서 이미지 이름

    @Temporal(TemporalType.DATE) // 계약서 작성일
    private Date contract_write_date;
}
