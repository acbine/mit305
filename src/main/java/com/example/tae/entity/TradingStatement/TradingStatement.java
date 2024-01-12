package com.example.tae.entity.TradingStatement;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TradingStatement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column
    private int store;
    /*
    @ManyToOne
    private ProcurementPlan procurementPlan;
     */
}
