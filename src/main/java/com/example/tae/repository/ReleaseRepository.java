package com.example.tae.repository;

import com.example.tae.entity.ReceivingProcessing.ReceivingProcessing;
import com.example.tae.entity.ReleaseProcess.ReleaseProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReleaseRepository extends JpaRepository<ReleaseProcess, Integer> {

    @Query("select rp from ReleaseProcess rp " +
        " where  rp.modDate between :date1 and :date2 " +
        "order by rp.modDate")
    List<ReleaseProcess> findByReleaseProcessWithModDate(@Param("date1") LocalDateTime date1, @Param("date2") LocalDateTime date2);
    @Query("select rp from ReleaseProcess rp" +
            " where rp.procurementPlan.procurementplan_code= :productCode and rp.modDate between :date1 and :date2 " +
            " order by rp.modDate")
    List<ReleaseProcess> findByReleaseProcessWithDateAndProductCode(@Param("productCode") int productCode, @Param("date1") LocalDateTime date1, @Param("date2") LocalDateTime date2);

    @Query("select rp from ReleaseProcess rp" +
            " where rp.procurementPlan.contract.productInformationRegistration.product_name= :productName and rp.modDate between :date1 and :date2 ")
    List<ReleaseProcess> findByReleaseProcessWithDateAndProductName(@Param("productName") String productName, @Param("date1") LocalDateTime date1, @Param("date2") LocalDateTime date2);


    @Query(value = "select * from release_process as rp, procurement_plan as pl " +
            " where rp.procurement_plan_procurementplan_code = :pcmPlanCode and pl.order_state = '마감' "+
            " order by mod_date desc limit 1", nativeQuery = true)
    Optional<ReleaseProcess> findTop1ByOrderByModDateDesc(@Param("pcmPlanCode") int pcmPlanCode);
}
