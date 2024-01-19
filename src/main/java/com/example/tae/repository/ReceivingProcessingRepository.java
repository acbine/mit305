package com.example.tae.repository;

import com.example.tae.entity.ReceivingProcessing.ReceivingProcessing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReceivingProcessingRepository extends JpaRepository<ReceivingProcessing,Integer> {

    @Query("select r from ReceivingProcessing r " +
            "where r.procurementPlan = :pcmPlanCode " +
            "order by r.modDate DESC limit 1")
    Optional<ReceivingProcessing> findTop1ByOrderByModDateDesc(@Param("pcmPlanCode") int pcmPlanCode);
}
