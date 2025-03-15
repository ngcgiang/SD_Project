package com.group02.sa_project.ittools.Model;

import java.util.List;
import jakarta.persistence.FetchType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId; // Ánh xạ với cột `category_id` trong database

    private String categoryName; // Ánh xạ với cột `category_name`

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Tool> tools; // Một category có nhiều tool
}
