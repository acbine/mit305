package com.example.tae.entity.Contract.dto;

import com.example.tae.entity.Contract.Contract;
import com.example.tae.entity.DummyData.Company;
import com.example.tae.entity.DummyData.DTO.CompanyDTO;
import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.entity.ProductInformation.dto.ProductInformationRegistrationDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractDTO {

    private int contract_code;

    private String payment_method;
    private int product_price;
    private int lead_time;

    @Builder.Default
    private List<ProductInformationRegistrationDto> productInformationRegistrationDtoList = new ArrayList<>();

    @Builder.Default
    private List<CompanyDTO> companyDTOList = new ArrayList<>();

    private Date start_date;
    private Date end_date;
    private Date contract_date;

    private boolean tf;

    public Contract contract() {

        return Contract.builder()
                .contract_code(contract_code)
                .payment_method(payment_method)
                .lead_time(lead_time)
                .productInformationRegistration((ProductInformationRegistration) productInformationRegistrationDtoList)
                .company((Company) companyDTOList)
                .start_date(start_date)
                .end_date(end_date)
                .contract_date(contract_date)
                .tf(tf)
                .build();
    }
}
