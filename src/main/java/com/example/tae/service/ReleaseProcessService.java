package com.example.tae.service;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ReleaseProcess.ReleaseProcess;
import com.example.tae.entity.ReleaseProcess.dto.ReleaseDto;

import java.util.List;

public interface ReleaseProcessService {
        ReleaseDto release(int release,int procurementPlan_code);

        int existence(int release,int procurementPlan_code) throws Exception;

        List<ReleaseDto> getStockDeliver() throws Exception;

        List<ReleaseDto> getStockDeliver(int state, String constraints);

}
