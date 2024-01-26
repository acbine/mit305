package com.example.tae.repository;

import com.example.tae.entity.Order.ProgressInspection;
import com.example.tae.entity.Order.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgressInspectionRepository extends JpaRepository<ProgressInspection, Integer> {

    @Query(" select PI from ProgressInspection PI" +
            " where PI.orderCode = :orderCode")
    List<ProgressInspection> findAllByOrderCode(@Param("orderCode") Purchase orderCode);
}
