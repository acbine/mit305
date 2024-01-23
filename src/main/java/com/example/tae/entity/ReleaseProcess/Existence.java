package com.example.tae.entity.ReleaseProcess;

import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.entity.TimeStamp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Existence extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    private ProductInformationRegistration productCode;

    private int releaseCNT;

    public Existence updateRelease(int num) {
        this.releaseCNT +=num;
        return Existence.this;
    }
}
