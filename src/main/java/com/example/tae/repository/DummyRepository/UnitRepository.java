package com.example.tae.repository.DummyRepository;

import com.example.tae.entity.DummyData.Classification.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit,Integer> {
}
