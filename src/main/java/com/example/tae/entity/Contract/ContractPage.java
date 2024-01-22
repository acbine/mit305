package com.example.tae.entity.Contract;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

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
    private int contractPage_code;

}
