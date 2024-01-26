package com.example.tae.entity.Contract;


import com.example.tae.entity.DummyData.Company;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
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
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contract_code;

    @ManyToOne
    ProductInformationRegistration productInformationRegistration;

    @ManyToOne
    Company company;

    String payment_method;
    int product_price;
    int lead_time;

    @Temporal(TemporalType.DATE) // TemporalType.DATE: 년-월-일 의 date로 매핑
    Date contract_date;

}
