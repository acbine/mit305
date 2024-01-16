package com.example.tae.entity.Contract;


import com.example.tae.entity.DummyData.Company;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contract_code;

    @ManyToOne(fetch = FetchType.LAZY)
    ProductInformationRegistration productInformationRegistration;

    @ManyToOne(fetch = FetchType.LAZY)
    Company company;

    String payment_method;
    int product_price;
    int lead_time;

    Date start_date;
    Date end_date;
    Date contract_date;

    boolean tf;
}
