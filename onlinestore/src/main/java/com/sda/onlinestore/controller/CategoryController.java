package com.sda.onlinestore.controller;

import com.sda.onlinestore.dto.CategoryDto;
import com.sda.onlinestore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("getCategories")
    public ResponseEntity<List<CategoryDto>> getCategories() {
        List<CategoryDto> categoryDtoList = categoryService.getAllCategories();
        return new ResponseEntity(categoryDtoList, HttpStatus.OK);
    }

    @DeleteMapping("deleteCategory/{id}")
    public ResponseEntity deleteCategory(@PathVariable Long id) {
        categoryService.deleteProduct(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("addCategory")
    public ResponseEntity addCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.addCategory(categoryDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("getCategory/{id}")
    public ResponseEntity<CategoryDto> getProduct(@PathVariable Long id) {
        CategoryDto categoryDto = categoryService.getCategory(id);
        return new ResponseEntity(categoryDto, HttpStatus.OK);

    }

    @PutMapping("editCategory")
    public ResponseEntity editCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.updateProduct(categoryDto);
        return new ResponseEntity(HttpStatus.OK);
    }

}
