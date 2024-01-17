package com.example.tae.service;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ReleaseProcess.ReleaseProcess;
import com.example.tae.repository.ReleaseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ReleaseProcessServiceImpl implements ReleaseProcessService{

    private ReleaseRepository releaseRepository;

    @Override
    public ReleaseProcess release(int release) {
        log.info("서비스단으로 넘어온 정보확인 : "+ release);
        ReleaseProcess releaseP = ReleaseProcess.builder()
                .releaseCNT(release)
                .build();
//        releaseRepository.save(releaseP);
        log.info("만들어진 재고값 보기 : "+releaseP);
        return releaseP;
    }

    @Override
    public List<ProcurementPlan> findProcurementPlans(int state, String constraints) {
        if(state == 0) {

        } else if(state == 1) {

        }
        return null;
    }
}
