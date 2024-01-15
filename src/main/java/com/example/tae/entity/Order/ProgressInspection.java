package com.example.tae.entity.Order;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
public class ProgressInspection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int progressInspectionNum;

    private Date progressInspectionPlan;
    private boolean progressInspectionStatus;

    @ManyToOne
    private Purchase purchase;
}