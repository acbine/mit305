package com.example.tae.repository.ProjectRepository;

import com.example.tae.entity.DummyData.Product.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,String> {
}
