package com.group02.sa_project.ittools.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group02.sa_project.ittools.Model.Category;
import com.group02.sa_project.ittools.Model.Tool;
import com.group02.sa_project.ittools.Repository.CategoryRepository;
import com.group02.sa_project.ittools.Service.ToolService;

@Controller
@RequestMapping("")
public class HomeController {
    @Autowired
    private ToolService toolService;

    private final CategoryRepository categoryRepository;

    public HomeController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("")
    public String show(Model model) {
        List<Category> allCategories = categoryRepository.findAllWithTools();
        List<Tool> allTools = toolService.getAllTools();
        model.addAttribute("categories", allCategories);
        model.addAttribute("tools", allTools);
        return "index";
    }
}