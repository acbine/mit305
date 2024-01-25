package com.example.tae.entity.Order;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProgressInspection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int progressInspectionNum;          // 진척 검수 순번

    private Date progressInspectionPlan;        // 진척 검수 계획
    private boolean progressInspectionStatus;   // 진척 검수 상태

    @ManyToOne
    private Purchase purchase;                  // 발주서
}
