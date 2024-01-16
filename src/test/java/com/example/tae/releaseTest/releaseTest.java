package com.example.tae.releaseTest;

import com.example.tae.entity.ReleaseProcess.ReleaseProcess;

import com.example.tae.repository.ReleaseRepository;
import com.example.tae.service.ReleaseProcessServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class releaseTest {
    @Autowired
    private ReleaseRepository releaseRepository;
    @Autowired
    private ReleaseProcessServiceImpl releaseProcessService;
    @Test
    public void entityCreateTest() {
        IntStream.rangeClosed(1,50).forEach(value -> {
            ReleaseProcess releaseProcess = releaseProcessService.release(value);
            System.out.println(releaseProcess);
        });
    }
}
