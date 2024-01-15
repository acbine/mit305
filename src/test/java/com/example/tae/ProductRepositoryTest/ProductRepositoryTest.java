package com.example.tae.ProductRepositoryTest;

import com.example.tae.entity.Product.Product;
import com.example.tae.entity.Product.ProductProductonPlan;
import com.example.tae.repository.ProductRepository.ProductProudctionPlanRepository;
import com.example.tae.repository.ProductRepository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class ProductRepositoryTest {
    @Autowired //제품 
    ProductRepository productRepository;

    @Autowired // 제품 생산꼐획
    ProductProudctionPlanRepository productProudctionPlanRepository;

    @Test
    public void ProductTest () throws ParseException {
        //제품 DB 안에 제품명 넣기
        Product product = Product.builder().productName("스마트폰").build();
        productRepository.save(product);


        String dateString = "2024-01-15";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        //에러나올시 -> 상위로 던지기함
        Date date=dateFormat.parse(dateString);


        //제품명을 기반으로 생산계획
        ProductProductonPlan productProductonPlan = ProductProductonPlan.builder().OutputeNum(11).ProjectOutputDate(date).product(product).build();
        productProudctionPlanRepository.save(productProductonPlan);

        System.out.println("데이터 DB에 들어감");

    }

}
