package com.example.tae.entity.DummyData;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Company {
    @Id
    String businessNumber;
    String departName;
    String businessName;
    String businessEmail;
    String fax;
    String businessTel;
}
