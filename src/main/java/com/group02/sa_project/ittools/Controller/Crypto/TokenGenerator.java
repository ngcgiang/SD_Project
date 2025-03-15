package com.group02.sa_project.ittools.Controller.Crypto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group02.sa_project.ittools.Model.Category;
import com.group02.sa_project.ittools.Model.Tool;
import com.group02.sa_project.ittools.Service.CategoryService;
import com.group02.sa_project.ittools.Service.ToolService;

@Controller
@RequestMapping("")
public class TokenGenerator {
    @Autowired
    private CategoryService categoriesService;
    @Autowired
    private ToolService toolService;

    @GetMapping("crypto/token_generator")
    public String show(Model model) {
        List<Category> allCategories = categoriesService.getAllCategories();
        List<Tool> allTools = toolService.getAllTools();
        model.addAttribute("tools", allTools);
        model.addAttribute("categories", allCategories);
        return "token_generator";
    }
}
