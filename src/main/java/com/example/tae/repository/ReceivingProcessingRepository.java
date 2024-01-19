package com.example.tae.repository;

import com.example.tae.entity.ProcurementPlan.ProcurementPlan;
import com.example.tae.entity.ReceivingProcessing.ReceivingProcessing;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReceivingProcessingRepository extends JpaRepository<ReceivingProcessing,Integer> {

    @Query("select r from ReceivingProcessing r " +
            "where r.procurementPlan = :pcmPlanCode " +
            "order by r.modDate DESC limit 1")
    Optional<ReceivingProcessing> findTop1ByOrderByModDateDesc(@Param("pcmPlanCode") int pcmPlanCode);

    @Query("SELECT pp FROM ProcurementPlan pp where pp.order_state='발주 전' ")
            //" INNER JOIN pp.contract c")
    List<ProcurementPlan> RECEIVING_PROCESSING_DTO_LIST(); //발주전 불러오기

    @Query("SELECT pp FROM ProcurementPlan pp where pp.procurementplan_code=:ppcode ")
    ProcurementPlan productplane (@Param("ppcode") int pp);

    @Modifying
    @Query("UPDATE ProcurementPlan pp SET pp.order_state='마감' WHERE pp.procurementplan_code=:ppcode ")
    int updateProcumentPlan (@Param("ppcode") int pp); //발주전을 마감으로

    @Query("SELECT rp FROM ReceivingProcessing rp where rp.procurementPlan.procurementplan_code=:ppcode ")
    ReceivingProcessing findByProcumentPlanCode (@Param("ppcode") int pp);


    Optional<ReceivingProcessing> findTop1ByOrderByModDateDesc();
}
