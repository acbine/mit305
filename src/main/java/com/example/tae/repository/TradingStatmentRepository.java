package com.example.tae.repository;

import com.example.tae.entity.TradingStatement.TradingStatement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradingStatmentRepository extends JpaRepository<TradingStatement,Integer> {
}
