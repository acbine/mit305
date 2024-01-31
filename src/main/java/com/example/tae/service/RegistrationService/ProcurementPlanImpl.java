package com.example.tae.service.RegistrationService;


import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ProcurementPlan.dto.ProcurementPlanJoinDTO;
import com.example.tae.repository.RegistrationRepository.ProcurementPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcurementPlanImpl implements ProcurementPlanService{

    @Autowired
    private ProcurementPlanRepository procurementPlanRepository;

    @Override
    public List<ProcurementPlanJoinDTO> getJoin(Date startDate, Date endDate) {

        List<Object[]> results = procurementPlanRepository.JoinResult(startDate, endDate);

        System.out.println();

        return results.stream()
                .map(result -> new ProcurementPlanJoinDTO(
                        (String) result[0], // 품목명
                        (int) result[1],// 품목 코드(현재는 int)
                        (int) result[2],// 제품의 생산량
                        (int) result[3],// 품목 소요량
                        (int) result[4],// L/T
                        (Date) result[5], // 제품 생산 날짜
                        (String) result[6], // 회사명
                        (int) result[7], // 생산 계획 코드
                        (int) result[8], // 계약 코드
                        (String) result[9] // 제품명
                )).collect(Collectors.toList());
    }

    @Override
    public List<ProcurementPlan> getAllPlan() {
        return procurementPlanRepository.findByProcurementPlanState();
    }

}
