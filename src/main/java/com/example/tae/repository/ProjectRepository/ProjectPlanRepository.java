package com.example.tae.repository.ProjectRepository;

import com.example.tae.entity.DummyData.Product.ProjectPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ProjectPlanRepository extends JpaRepository<ProjectPlan,Integer> {

    @Query("select p from ProjectPlan p " +
            "where p.projectOutputDate " +
            "between :startDate " +
            "and :endDate")
    List<ProjectPlan> findByDateBetween(Date startDate, Date endDate);
}
