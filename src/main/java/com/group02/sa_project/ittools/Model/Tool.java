package com.group02.sa_project.ittools.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tools")
@Getter
@Setter
@NoArgsConstructor
public class Tool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tid; // Ánh xạ với cột 'tid' trong database

    @Column(name = "tool_name", nullable = false, unique = true, length = 100)
    private String toolName; // Ánh xạ với cột 'tool_name'

    @Column(name = "description", nullable = false, length = 100)
    private String description; // Ánh xạ với cột 'description'

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category; // Ánh xạ với khóa ngoại 'category_id'

    @Column(name = "enabled", columnDefinition = "BOOLEAN DEFAULT true")
    private boolean enabled;

    @Column(name = "premium_required", columnDefinition = "BOOLEAN DEFAULT false")
    private boolean premiumRequired;
}
