package com.example.tae.service;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ReleaseProcess.ReleaseProcess;

import java.util.List;

public interface ReleaseProcessService {
        ReleaseProcess release(int release);

        int existence(int release);

        List<ProcurementPlan> findProcurementPlans(int state, String constraints);
}
