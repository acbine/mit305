package com.example.tae.injectInfo;

import com.example.tae.service.BinDummyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
// H2 DB에 잘 넣어지느지에 대한 테스트
@SpringBootTest
public class BinH2DBDummyInectionTest {
    @Autowired
    BinDummyService binDummyService;

    @Test
    public void injection(){
        binDummyService.binDummyinjection();
    }
}
