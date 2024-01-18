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
@ToString
@Getter
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

    Date start_date;
    Date end_date;
    Date contract_date;

    boolean tf;
}
