package com.example.tae.entity.Order;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.TimeStamp;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Purchase extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ordercode;                       // 발주서 코드

    @OneToMany(mappedBy = "purchase", fetch = FetchType.LAZY)
    private List<ProcurementPlan> procurementPlan;  // 조달 계획 리스트

    @OneToMany
    private List<ProgressInspection> progressInspections;
}