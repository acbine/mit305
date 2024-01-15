package com.example.tae.entity.Order;

import jakarta.persistence.*;
import java.util.Date;

public class ProgressInspection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int progressInspectionNum;

    private Date progressInspectionPlan;
    private boolean progressInspectionStatus;

    @OneToMany
    private Purchase purchase;
}