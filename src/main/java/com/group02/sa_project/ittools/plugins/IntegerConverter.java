package com.group02.sa_project.ittools.plugins;

import com.group02.sa_project.ittools.Model.Category;
import com.group02.sa_project.ittools.Model.Tool;
import com.group02.sa_project.ittools.Service.CategoryService;
import com.group02.sa_project.ittools.Service.ToolService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;
import java.util.HashMap;

@Controller
@RequestMapping("")
public class IntegerConverter {
    @Autowired
    private CategoryService categoriesService;

    @Autowired
    private ToolService toolService;

    @GetMapping("converter/integer_base_converter")
    public String show(Model model) {
        List<Category> allCategories = categoriesService.getAllCategories();
        List<Tool> allTools = toolService.getAllTools();
        model.addAttribute("tools", allTools);
        model.addAttribute("categories", allCategories);
        return "integer_base_converter";
    }

    @RestController
    @RequestMapping("/api/integer-base-converter")
    public class IntegerConverterController {

        private final String charset = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz+/";

        @PostMapping
        public Map<String, String> convertInteger(
                @RequestParam String number,
                @RequestParam int base,
                @RequestParam int targetBase) {

            if (base < 2 || base > 64 || targetBase < 2 || targetBase > 64) {
                throw new IllegalArgumentException("Base must be between 2 and 64");
            }

            // Chuyển từ base hiện tại về hệ 10
            int decimalValue = convertToDecimal(number, base);

            // Chuyển từ hệ 10 sang targetBase
            String result = convertFromDecimal(decimalValue, targetBase);

            // Trả về kết quả dạng JSON
            Map<String, String> response = new HashMap<>();
            response.put("result", result);
            return response;
        }

        private int convertToDecimal(String number, int base) {
            int decimalValue = 0;
            for (char c : number.toCharArray()) {
                int digitValue = charset.indexOf(c);
                if (digitValue == -1 || digitValue >= base) {
                    throw new IllegalArgumentException("Invalid character for base " + base);
                }
                decimalValue = decimalValue * base + digitValue;
            }
            return decimalValue;
        }

        private String convertFromDecimal(int decimal, int base) {
            if (decimal == 0) return "0";
            StringBuilder result = new StringBuilder();
            while (decimal > 0) {
                result.insert(0, charset.charAt(decimal % base));
                decimal /= base;
            }
            return result.toString();
        }
    }
}
