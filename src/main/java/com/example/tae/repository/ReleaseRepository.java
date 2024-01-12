package com.example.tae.repository;

import com.example.tae.entity.ReleaseProcess.ReleaseProcess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReleaseRepository extends JpaRepository<ReleaseProcess, Integer> {
}
