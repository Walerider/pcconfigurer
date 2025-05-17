package com.walerider.pcconfigurer.services;

import com.walerider.pcconfigurer.DTO.category.CategoryResponse;
import com.walerider.pcconfigurer.DTO.category.CreateCategoryRequest;
import com.walerider.pcconfigurer.entities.Category;
import com.walerider.pcconfigurer.repositories.CategoryRepository;
import com.walerider.pcconfigurer.validation.exceptions.BadRequestException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void create(CreateCategoryRequest request) {
        if (categoryRepository.existsByName(request.getName())) {
            throw new BadRequestException("the name must be unique");
        }
        categoryRepository.save(Category.builder()
                .name(request.getName()).build());
    }

    public List<CategoryResponse> getAll() {
        return categoryRepository.findAll().stream()
                .map((category) -> CategoryResponse.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .build())
                .toList();
    }
}
