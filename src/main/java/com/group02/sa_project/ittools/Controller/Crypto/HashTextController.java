package com.group02.sa_project.ittools.Controller.Crypto;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.security.MessageDigest;

import com.group02.sa_project.ittools.Service.CategoryService;
import com.group02.sa_project.ittools.Model.Category;

import com.group02.sa_project.ittools.Service.ToolService;
import com.group02.sa_project.ittools.Model.Tool;

@Controller
@RequestMapping("")
public class HashTextController {
    @Autowired
    private CategoryService categoriesService;
    @Autowired
    private ToolService toolService;

    @GetMapping("crypto/hash_text")
    public String show(Model model) {
        List<Category> allCategories = categoriesService.getAllCategories();
        List<Tool> allTools = toolService.getAllTools();
        model.addAttribute("tools", allTools);
        model.addAttribute("categories", allCategories);
        return "hash_text";
    }

    @RestController
    @RequestMapping("/api/hash")
    public class HashController {
      @PostMapping
        public ResponseEntity<HashResult> hashText(@RequestParam String text,@RequestParam String encoding ,@RequestParam String algorithm) {
            try {
                MessageDigest digest = MessageDigest.getInstance(algorithm);
                byte[] hashBytes = digest.digest(text.getBytes());
        
                // Xử lý encode theo loại người dùng chọn
                String hashResult;
                switch (encoding) {
                    case "base64":
                        hashResult = java.util.Base64.getEncoder().encodeToString(hashBytes);
                        break;
                    case "binary":
                        StringBuilder binaryString = new StringBuilder();
                        for (byte b : hashBytes) {
                            binaryString.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
                        }
                        hashResult = binaryString.toString();
                        break;
                    default: // Mặc định là Hex
                        StringBuilder hexString = new StringBuilder();
                        for (byte b : hashBytes) {
                            String hex = Integer.toHexString(0xff & b);
                            if (hex.length() == 1) hexString.append('0');
                            hexString.append(hex);
                        }
                        hashResult = hexString.toString();
                }
        
                return ResponseEntity.ok(new HashResult(hashResult));
            } catch (NoSuchAlgorithmException e) {
                return ResponseEntity.badRequest().body(new HashResult("Invalid algorithm"));
            }
        }

        // Đối tượng trả về JSON
        static class HashResult {
            public String hash;

            public HashResult(String hash) {
                this.hash = hash;
            }
        } 
    }
}