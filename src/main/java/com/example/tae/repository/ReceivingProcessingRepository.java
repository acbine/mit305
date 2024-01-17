package com.example.tae.repository;

import com.example.tae.entity.ReceivingProcessing.ReceivingProcessing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReceivingProcessingRepository extends JpaRepository<ReceivingProcessing,Integer> {
    Optional<ReceivingProcessing> findDistinctFirstBy();
}
