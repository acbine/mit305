package com.example.tae.repository.ProjectRepository;

import com.example.tae.entity.DummyData.Product.ProjectPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ProjectPlanRepository extends JpaRepository<ProjectPlan,Integer> {

    @Query("select p from ProjectPlan p " +
            "where p.projectOutputDate " +
            "between :startDate " +
            "and :endDate")
    List<ProjectPlan> findByDateBetween(Date startDate, Date endDate);

    @Modifying
    @Query("UPDATE ProjectPlan pj SET pj.outPuteNum= :updateNumber , pj.projectOutputDate=:upDateDate  WHERE pj.id=:pjcode")
    void updateProjectPlan(@Param("pjcode") int updateprojectCode ,@Param("updateNumber") int updateNumber , @Param("upDateDate") Date upDateDate); //생산계획 수정하는 쿼리문

}
