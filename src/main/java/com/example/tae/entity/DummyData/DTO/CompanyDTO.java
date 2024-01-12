package com.example.tae.entity.DummyData.DTO;

import com.example.tae.entity.DummyData.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {
    String businessNumber;
    String departName;
    String businessName;
    String businessEmail;
    String fax;
    String businessTel;

    public Company com() {
        return Company.builder()
                .businessNumber(businessNumber)
                .businessName(businessName)
                .departName(departName)
                .businessTel(businessTel)
                .fax(fax)
                .businessEmail(businessEmail)
                .build();
    }
}
