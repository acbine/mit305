package com.example.tae.entity.ReceivingProcessing;

import com.example.tae.entity.TimeStamp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
/**
 * 입고처리 관련 엔티티(테이블)
 */


@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReceivingProcessing extends TimeStamp {
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
