package com.group02.sa_project.ittools.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.group02.sa_project.ittools.Model.Tool;

public interface ToolRepository extends JpaRepository<Tool, Integer> {
  
} 
