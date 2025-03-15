package com.group02.sa_project.ittools.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group02.sa_project.ittools.Model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
