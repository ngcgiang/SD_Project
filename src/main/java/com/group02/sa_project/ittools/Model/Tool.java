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
    private Long tid; // Ánh xạ với cột 'tid' trong database

    @Column(name = "tool_name", nullable = false, unique = true, length = 100)
    private String toolName; // Ánh xạ với cột 'tool_name'

    @Column(name = "description", nullable = false, length = 100)
    private String description; // Ánh xạ với cột 'description'

    @Column(name = "pathtool", nullable = false, length = 100)
    private String pathTool; // Ánh xạ với cột 'pathTool'

    @ManyToOne
    @JoinColumn(name = "category_id") // Khóa ngoại
    private Category category; 

    @Column(name = "enabled")
    private boolean enabled = true; // Mặc định true

    @Column(name = "premium_required")
    private boolean premiumRequired = false; // Mặc định false
}
