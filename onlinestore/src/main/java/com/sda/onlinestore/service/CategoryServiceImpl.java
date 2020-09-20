package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.CategoryDto;
import com.sda.onlinestore.model.CategoryModel;
import com.sda.onlinestore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto getCategory(Long id) {
        Optional<CategoryModel> categoryModelOptional = categoryRepository.findById(id);

        if (categoryModelOptional.isPresent()) {
            CategoryModel categoryModel = categoryModelOptional.get();
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(categoryModel.getId());
            categoryDto.setName(categoryModel.getName());

            CategoryModel parentCategoryModel = categoryModel.getParentCategory();
            CategoryDto parentCategoryDto = new CategoryDto();
            parentCategoryDto.setId(parentCategoryModel.getId());
            parentCategoryDto.setName(parentCategoryModel.getName());
            categoryDto.setParentCategory(parentCategoryDto);

            List<CategoryModel> subcategoryListModel = categoryModel.getSubcategory();
            List<CategoryDto> subcategories = new ArrayList<>();
            for(CategoryModel subcategoryModel : subcategoryListModel) {
                CategoryDto subcategoryDto = new CategoryDto();
                subcategoryDto.setId(subcategoryModel.getId());
                subcategoryDto.setName(subcategoryModel.getName());
                subcategories.add(subcategoryDto);
            }
            categoryDto.setSubcategory(subcategories);

            return categoryDto;
        }
        return null;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<CategoryModel> categoryModels = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = new ArrayList<>();

        for (CategoryModel categoryModel : categoryModels) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(categoryModel.getId());
            categoryDto.setName(categoryModel.getName());

            CategoryModel parentCategoryModel = categoryModel.getParentCategory();
            CategoryDto parentCategoryDto = new CategoryDto();
            parentCategoryDto.setId(parentCategoryModel.getId());
            parentCategoryDto.setName(parentCategoryModel.getName());
            categoryDto.setParentCategory(parentCategoryDto);


            List<CategoryModel> subcategoryListModel = categoryModel.getSubcategory();
            List<CategoryDto> subcategories = new ArrayList<>();
            for(CategoryModel subcategoryModel : subcategoryListModel) {
                CategoryDto subcategoryDto = new CategoryDto();
                subcategoryDto.setId(subcategoryModel.getId());
                subcategoryDto.setName(subcategoryModel.getName());
                subcategories.add(subcategoryDto);
            }
            categoryDto.setSubcategory(subcategories);

            categoryDtoList.add(categoryDto);
        }
        return categoryDtoList;
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void addCategory(CategoryDto categoryDto) {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setName(categoryDto.getName());

        categoryRepository.save(categoryModel);
    }

    @Override
    public void updateProduct(CategoryDto categoryDto) {
        Optional<CategoryModel> foundCategory = categoryRepository.findById(categoryDto.getId());
        if (foundCategory.isPresent()) {
            CategoryModel categoryModel = foundCategory.get();
            categoryModel.setName(categoryDto.getName());

            categoryRepository.save(categoryModel);
        }
    }
}
