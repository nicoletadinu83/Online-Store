package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.AuthorDto;
import com.sda.onlinestore.dto.CategoryDto;
import com.sda.onlinestore.dto.ProductDto;
import com.sda.onlinestore.model.AuthorModel;
import com.sda.onlinestore.model.CategoryModel;
import com.sda.onlinestore.model.ProductModel;
import com.sda.onlinestore.repository.CategoryRepository;
import com.sda.onlinestore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public CategoryDto getCategory(Long id) {
        Optional<CategoryModel> categoryModelOptional = categoryRepository.findById(id);

        if (categoryModelOptional.isPresent()) {
            CategoryModel categoryModel = categoryModelOptional.get();
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(categoryModel.getId());
            categoryDto.setName(categoryModel.getName());

            if (categoryModel.getParentCategory() != null) {
                CategoryModel parentCategoryModel = categoryModel.getParentCategory();
                CategoryDto parentCategoryDto = new CategoryDto();
                parentCategoryDto.setId(parentCategoryModel.getId());
                parentCategoryDto.setName(parentCategoryModel.getName());
                categoryDto.setParentCategory(parentCategoryDto);
            }

            if (categoryModel.getSubcategory() != null) {
                List<CategoryModel> subcategoryListModel = categoryModel.getSubcategory();
                List<CategoryDto> subcategories = new ArrayList<>();
                for (CategoryModel subcategoryModel : subcategoryListModel) {
                    CategoryDto subcategoryDto = new CategoryDto();
                    subcategoryDto.setId(subcategoryModel.getId());
                    subcategoryDto.setName(subcategoryModel.getName());
                    subcategories.add(subcategoryDto);
                }
                categoryDto.setSubcategory(subcategories);
            }
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

            if (categoryModel.getParentCategory() != null) {
                CategoryModel parentCategoryModel = categoryModel.getParentCategory();
                CategoryDto parentCategoryDto = new CategoryDto();
                parentCategoryDto.setId(parentCategoryModel.getId());
                parentCategoryDto.setName(parentCategoryModel.getName());
                categoryDto.setParentCategory(parentCategoryDto);
            }

            if (categoryModel.getSubcategory() != null) {
                List<CategoryModel> subcategoryListModel = categoryModel.getSubcategory();
                List<CategoryDto> subcategories = new ArrayList<>();
                for (CategoryModel subcategoryModel : subcategoryListModel) {
                    CategoryDto subcategoryDto = new CategoryDto();
                    subcategoryDto.setId(subcategoryModel.getId());
                    subcategoryDto.setName(subcategoryModel.getName());
                    subcategories.add(subcategoryDto);
                }
                categoryDto.setSubcategory(subcategories);
            }
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
        CategoryDto parentCategoryDto = categoryDto.getParentCategory();
        if (parentCategoryDto != null) {
            Optional<CategoryModel> parentCategoryFound = categoryRepository.findById(parentCategoryDto.getId());
            if (parentCategoryFound.isPresent()) {
                CategoryModel parentCategoryModel = parentCategoryFound.get();
                categoryModel.setParentCategory(parentCategoryModel);
            }
        }

        categoryRepository.save(categoryModel);
    }

    @Override
    public void updateCategory(CategoryDto categoryDto) {

        Optional<CategoryModel> foundCategory = categoryRepository.findById(categoryDto.getId());

        if (foundCategory.isPresent()) {
            CategoryModel categoryModel = foundCategory.get();
            categoryModel.setName(categoryDto.getName());
            if (categoryDto.getParentCategory() != null) {
                long id = categoryDto.getParentCategory().getId();
                if (categoryRepository.findById(id).isPresent()) {
                    categoryModel.setParentCategory(categoryRepository.findById(id).get());
                }
            }
            categoryRepository.save(categoryModel);
        }

    }

    @Override
    public AuthorModel getAuthorModelFromService(ProductDto productDto){
        AuthorDto authorDto = productDto.getAuthor();
        AuthorModel authorModel = new AuthorModel();

        authorModel.setId(authorDto.getId());
        authorModel.setFirstName(authorDto.getFirstName());
        authorModel.setLastName(authorDto.getLastName());
        return authorModel;
    }

    @Override
    public AuthorDto getAuthorFromService(ProductModel productModel){
        AuthorModel authorModel = productModel.getAuthor();
        AuthorDto authorDto = new AuthorDto();

        authorDto.setId(authorModel.getId());
        authorDto.setFirstName(authorModel.getFirstName());
        authorDto.setLastName(authorModel.getLastName());
        return authorDto;
    }

    @Override
    public List<ProductDto> getProductsByCategory(CategoryDto categoryDto) {
        List<ProductDto> productByCategoryDto = new ArrayList<>();
        List<ProductModel> productByCategory = new ArrayList<>();
        Optional<CategoryModel> categoryModelOptional = categoryRepository.findById(categoryDto.getId());
        List<ProductModel> allProducts = productRepository.findAll();
        if (categoryModelOptional.isPresent()) {
            CategoryModel categoryModel = categoryModelOptional.get();
            for (ProductModel product : allProducts) {
                if (product.getCategory().getId() == categoryModel.getId()) {
                    productByCategory.add(productRepository.findById(product.getId()).get());
                }
            }

            for (ProductModel product : productByCategory) {
                ProductDto productDto = new ProductDto();

                productDto.setId(product.getId());
                productDto.setTitle(product.getTitle());
                productDto.setProductType(product.getProductType());
                productDto.setThumbnail(product.getThumbnail());
                productDto.setPrice(product.getPrice());
                productDto.setCategory(product.getCategory());
                productDto.setAuthor(getAuthorFromService(product));
            }
        }

        return productByCategoryDto;
    }

}

