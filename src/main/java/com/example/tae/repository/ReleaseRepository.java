package com.example.tae.repository;

import com.example.tae.entity.ReleaseProcess.ReleaseProcess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReleaseRepository extends JpaRepository<ReleaseProcess, Integer> {

    ReleaseProcess findTop1ByOrderByModDateDesc();
}
