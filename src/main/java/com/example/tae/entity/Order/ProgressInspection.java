package com.example.tae.entity.Order;

import com.example.tae.entity.ProductInformation.ProductInformationRegistration;
import com.example.tae.entity.TimeStamp;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;

import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProgressInspection extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int progressInspectionNum;          // 진척 검수 순번
    @ManyToOne
    private Purchase orderCode;                       //발주서코드
    private Date progressInspectionPlan;        // 진척 검수 계획
    @Column(nullable = true)
    private boolean progressInspectionStatus;   // 진척 검수 상태

}
