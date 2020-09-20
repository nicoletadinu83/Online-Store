package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto getCategory(Long id);

    List<CategoryDto> getAllCategories();

    void deleteCategory(Long id);

    void addCategory(CategoryDto categoryDto);

    void updateProduct(CategoryDto categoryDto);
}
