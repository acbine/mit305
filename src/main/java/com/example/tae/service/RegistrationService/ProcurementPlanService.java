package com.example.tae.service.RegistrationService;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ProcurementPlan.dto.ProcurementPlanJoinDTO;

import java.util.Date;
import java.util.List;

public interface ProcurementPlanService {

    List<ProcurementPlanJoinDTO> getJoin(Date startDate, Date endDate);

    List<ProcurementPlan> getAllPlan();
}
