package com.walerider.pcconfigurer.Controllers;

import com.walerider.pcconfigurer.DTO.category.CategoryResponse;
import com.walerider.pcconfigurer.DTO.category.CreateCategoryRequest;
import com.walerider.pcconfigurer.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryRestController {
    private final CategoryService categoryService;

    @PostMapping("/create")
    public String create(@RequestBody CreateCategoryRequest request) {
        categoryService.create(request);
        return "category created";
    }

    @GetMapping("get_all")
    public List<CategoryResponse> getAll() {
        return categoryService.getAll();
    }
}
