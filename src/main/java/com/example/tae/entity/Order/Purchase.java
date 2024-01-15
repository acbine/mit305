package com.example.tae.entity.Order;

import com.example.tae.entity.TimeStamp;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
public class Purchase extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ordercode;

    /*@OneToMany
    private List<ProcurementPlan> procurementPlan;*/
}