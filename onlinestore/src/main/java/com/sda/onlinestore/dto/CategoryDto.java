package com.sda.onlinestore.dto;

import com.sda.onlinestore.model.CategoryModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDto {

    private long id;
    private String name;
    private List<CategoryModel> subcategory= new ArrayList<>();
    private CategoryModel parentCategory;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryModel> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(List<CategoryModel> subcategory) {
        this.subcategory = subcategory;
    }

    public CategoryModel getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(CategoryModel parentCategory) {
        this.parentCategory = parentCategory;
    }
}
