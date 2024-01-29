package com.example.tae.repository;

import com.example.tae.entity.Order.ProgressInspection;
import com.example.tae.entity.Order.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.management.ValueExp;
import java.util.List;

public interface ProgressInspectionRepository extends JpaRepository<ProgressInspection, Integer> {

    @Query(" select PI from ProgressInspection PI" +
            " where PI.orderCode = :orderCode")
    List<ProgressInspection> findAllByOrderCode(@Param("orderCode") Purchase orderCode);

    @Query(" select PI from ProgressInspection  PI "+
        " where PI.orderCode = :orderCode" +
        " order by PI.progressInspectionPlan desc ")
    List<ProgressInspection> findByOrderCode(@Param("orderCode") Purchase orderCode);

    @Query(value =  "select  PI from  ProgressInspection  PI " +
                "where PI.orderCode = :orderCode " +
            "order by PI.progressInspectionPlan limit 1", nativeQuery = true)
    ProgressInspection findOneInspectorByOrderCode(@Param("orderCode") Purchase orderCode);

    @Query(value = "SELECT * FROM tae.progress_inspection " +
            "WHERE progress_inspection_num = :inspectorId" +
            " UNION " +
            " SELECT * FROM progress_inspection pr" +
            " WHERE order_code_order_code = ( " +
            "    SELECT order_code_order_code " +
            "    FROM tae.progress_inspection " +
            "    WHERE progress_inspection_num = :inspectorId)", nativeQuery = true)
    List<ProgressInspection> findByProgressInspectionIdAndCheckToStatus(@Param("inspectorId") int inspectorId);
}
