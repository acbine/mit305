package com.example.tae.repository.DummyRepository;

import com.example.tae.entity.DummyData.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, String> {
}
