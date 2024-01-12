package com.example.tae.injectInfo;

import com.example.tae.service.DummyServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class companyInjection {

    @Autowired
    private DummyServiceImpl companyService;
    @Test
   public void injectCompany() {
        companyService.dummyDataInjection();
    }
}
