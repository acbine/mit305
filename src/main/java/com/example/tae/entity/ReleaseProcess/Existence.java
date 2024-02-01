package com.example.tae.entity.ReleaseProcess;

import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.entity.TimeStamp;
import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@ToString
public class Existence extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    private ProductInformationRegistration productCode;

    private int releaseCNT;

    public Existence updateRelease(int num) {
        return Existence.builder()
                .id(this.id)
                .productCode(this.getProductCode())
                .releaseCNT(this.releaseCNT +=num)
                .build();
    }
}
