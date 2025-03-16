package com.group02.sa_project.ittools.Controller.Crypto;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group02.sa_project.ittools.Model.Category;
import com.group02.sa_project.ittools.Model.Tool;
import com.group02.sa_project.ittools.Service.CategoryService;
import com.group02.sa_project.ittools.Service.ToolService;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Map;


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

// Controller xử lý API trả về JSON
@RestController
@RequestMapping("/api/token")
class TokenController {
    @PostMapping
    public Map<String, String> generateToken(
            @RequestParam int length,
            @RequestParam boolean includeUppercase,
            @RequestParam boolean includeLowercase,
            @RequestParam boolean includeNumbers,
            @RequestParam boolean includeSymbols) {

        String uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseChars = "abcdefghijklmnopqrstuvwxyz";
        String numberChars = "0123456789";
        String symbolChars = "!@#$%^&*()_+-=[]{}|;:,.<>?";

        StringBuilder characterPool = new StringBuilder();
        if (includeUppercase) characterPool.append(uppercaseChars);
        if (includeLowercase) characterPool.append(lowercaseChars);
        if (includeNumbers) characterPool.append(numberChars);
        if (includeSymbols) characterPool.append(symbolChars);

        if (characterPool.length() == 0) {
            throw new IllegalArgumentException("At least one character type must be selected.");
        }

        SecureRandom random = new SecureRandom();
        StringBuilder token = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characterPool.length());
            token.append(characterPool.charAt(index));
        }

        Map<String, String> response = new HashMap<>();
        response.put("token", token.toString());
        return response;
    }
}
