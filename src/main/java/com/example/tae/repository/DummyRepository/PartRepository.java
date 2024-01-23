package com.example.tae.repository.DummyRepository;

import com.example.tae.entity.DummyData.Classification.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {
}
