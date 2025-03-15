package com.group02.sa_project.ittools.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

import com.group02.sa_project.ittools.Model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("SELECT c FROM Category c JOIN FETCH c.tools")
    List<Category> findAllWithTools();
}
