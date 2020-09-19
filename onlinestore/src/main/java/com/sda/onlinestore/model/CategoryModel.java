package com.sda.onlinestore.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="category")
public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
     private String name;
     @OneToMany
     @JoinColumn
     private List<CategoryModel> subcategory;
     private CategoryModel parentCategory;

    public List<CategoryModel> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(List<CategoryModel> subcategory) {
        this.subcategory = subcategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
