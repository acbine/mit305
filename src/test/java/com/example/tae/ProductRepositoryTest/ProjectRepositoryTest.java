package com.example.tae.ProductRepositoryTest;

import com.example.tae.entity.DummyData.Product.Project;
import com.example.tae.entity.DummyData.Product.ProjectPlan;
import com.example.tae.repository.ProjectRepository.ProjectPlanRepository;
import com.example.tae.repository.ProjectRepository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class ProjectRepositoryTest {
    @Autowired //제품 
    ProjectRepository projectRepository;

    @Autowired // 제품 생산꼐획
    ProjectPlanRepository projectPlanRepository;

    @Test
    public void ProductTest () throws ParseException {
        //제품 DB 안에 제품명 넣기
        Project product = Project.builder().productName("스마트폰").build();
        projectRepository.save(product);


        String dateString = "2024-01-15";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        //에러나올시 -> 상위로 던지기함
        Date date=dateFormat.parse(dateString);


        //제품명을 기반으로 생산계획
        ProjectPlan projectPlan = ProjectPlan.builder().outPuteNum(11).projectOutputDate(date).product(product).build();
        projectPlanRepository.save(projectPlan);

        System.out.println("데이터 DB에 들어감");

    }

}
