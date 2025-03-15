package com.group02.sa_project.ittools.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group02.sa_project.ittools.Model.Tool;
import com.group02.sa_project.ittools.Repository.ToolRepository;

@Service
public class ToolService {
        @Autowired
    private ToolRepository toolRepository;

    public List<Tool> getAllTools() {
        return toolRepository.findAll();
    }
}
