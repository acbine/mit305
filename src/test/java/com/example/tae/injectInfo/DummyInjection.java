package com.example.tae.injectInfo;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.repository.ReceivingProcessingRepository;
import com.example.tae.service.dummyService.DummyServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class DummyInjection {

    @Autowired
    private DummyServiceImpl companyService;
    @Autowired
    private ReceivingProcessingRepository receivingProcessingRepository;

    @Test
   public void injectCompany() {
        companyService.dummyDataInjection();
    }

}
