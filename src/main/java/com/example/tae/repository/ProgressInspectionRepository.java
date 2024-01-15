package com.example.tae.repository;

import com.example.tae.entity.Order.ProgressInspection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressInspectionRepository extends JpaRepository<ProgressInspection, Integer> {
}
