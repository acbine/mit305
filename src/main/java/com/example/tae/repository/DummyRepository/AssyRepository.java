package com.example.tae.repository.DummyRepository;

import com.example.tae.entity.DummyData.Classification.Assy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssyRepository extends JpaRepository<Assy,Integer> {
}
