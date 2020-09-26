package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.AuthorDto;
import com.sda.onlinestore.dto.CategoryDto;
import com.sda.onlinestore.dto.ProductDto;
import com.sda.onlinestore.model.AuthorModel;
import com.sda.onlinestore.model.CategoryModel;
import com.sda.onlinestore.model.ProductModel;
import com.sda.onlinestore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    // ProductModel productModel;
    // ProductDto productDto;

    public void setParentCategory(ProductModel productModel) {
        CategoryModel categoryModel = productModel.getCategory();
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(categoryModel.getId());
        categoryDto.setName(categoryModel.getName());

        CategoryModel parentModelCategory = categoryModel.getParentCategory();
        CategoryDto parentDtoCategory = new CategoryDto();
        parentDtoCategory.setId(parentModelCategory.getId());
        parentDtoCategory.setName(parentModelCategory.getName());

        categoryDto.setParentCategory(parentDtoCategory);
    }
    public AuthorDto getAuthorFromService(ProductModel productModel){
        AuthorModel authorModel = productModel.getAuthor();
        AuthorDto authorDto = new AuthorDto();

        authorDto.setId(authorModel.getId());
        authorDto.setFirstName(authorModel.getFirstName());
        authorDto.setLastName(authorModel.getLastName());
        return authorDto;
    }

    public AuthorModel getAuthorModelFromService(ProductDto productDto){
        AuthorDto authorDto = productDto.getAuthor();
        AuthorModel authorModel = new AuthorModel();

        authorModel.setId(authorDto.getId());
        authorModel.setFirstName(authorDto.getFirstName());
        authorModel.setLastName(authorDto.getLastName());
        return authorModel;
    }

    @Override
    public ProductDto getProduct(Long id) {

        Optional<ProductModel> foundProductModel = productRepository.findById(id);
        if (foundProductModel.isPresent()) {
            ProductModel productModel = foundProductModel.get();
            ProductDto productDto = new ProductDto();

           // productDto.setCategory(productModel.getCategory());
            //Every Product has a CAtegory Model which include a parentCategory->Category type
            CategoryDto categoryDto= new CategoryDto();

          //  categoryDto.setParentCategory(setParentCategory(ProductModel productModel);
            productDto.setCategory(categoryDto);
            productDto.setId(productModel.getId());
            productDto.setPrice(productModel.getPrice());
            productDto.setProductType(productModel.getProductType());
            productDto.setThumbnail(productModel.getThumbnail());
            productDto.setTitle(productModel.getTitle());

            productDto.setAuthor(getAuthorFromService(productModel));
            return productDto;
        }
        return null;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<ProductModel> productModelList = productRepository.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();

        for (ProductModel productModel : productModelList) {
            ProductDto productDto = new ProductDto();
            productDto.setId(productModel.getId());
            productDto.setTitle(productModel.getTitle());
            productDto.setProductType(productModel.getProductType());
            productDto.setThumbnail(productModel.getThumbnail());
            productDto.setPrice(productModel.getPrice());

            CategoryModel categoryModel = productModel.getCategory();
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(categoryModel.getId());
            categoryDto.setName(categoryModel.getName());

            CategoryModel parentModelCategory = categoryModel.getParentCategory();
            CategoryDto parentDtoCategory = new CategoryDto();
            parentDtoCategory.setId(parentModelCategory.getId());
            parentDtoCategory.setName(parentModelCategory.getName());

            categoryDto.setParentCategory(parentDtoCategory);

            productDto.setCategory(categoryDto);
            productDto.setAuthor(getAuthorFromService(productModel));

            productDtoList.add(productDto);
        }

        return productDtoList;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void addProduct(ProductDto productDto) {
        ProductModel productModel = new ProductModel();
        productModel.setId(productDto.getId());
        productModel.setAuthor(getAuthorModelFromService(productDto));
        CategoryDto categoryDto=productDto.getCategory();
        CategoryModel categoryModel= new CategoryModel();

        categoryModel.setId(categoryDto.getId());
        categoryModel.setName(categoryDto.getName());
        CategoryDto parentDtoCategory= categoryDto.getParentCategory();
        CategoryModel parentModelCategory= new CategoryModel();

        parentModelCategory.setId(parentDtoCategory.getId());
        parentModelCategory.setName(parentDtoCategory.getName());

        categoryModel.setParentCategory(parentModelCategory);

        productModel.setCategory(categoryModel);
        productModel.setPrice(productDto.getPrice());
        productModel.setProductType(productDto.getProductType());
        productModel.setThumbnail(productDto.getThumbnail());
        productModel.setTitle(productDto.getTitle());

        productRepository.save(productModel);

    }

    @Override
    public void updateProduct(ProductDto productDto) {
        Optional<ProductModel> productModelFind = productRepository.findById(productDto.getId());
        if (productModelFind.isPresent()) {
            ProductModel productModel = productModelFind.get();
            productModel.setId(productDto.getId());
            productModel.setAuthor(getAuthorModelFromService(productDto));

            CategoryDto categoryDto=productDto.getCategory();
            CategoryModel categoryModel= new CategoryModel();

            categoryModel.setId(categoryDto.getId());
            categoryModel.setName(categoryDto.getName());
            CategoryDto parentDtoCategory= categoryDto.getParentCategory();
            CategoryModel parentModelCategory= new CategoryModel();

            parentModelCategory.setId(parentDtoCategory.getId());
            parentModelCategory.setName(parentDtoCategory.getName());

            categoryModel.setParentCategory(parentModelCategory);

            productModel.setCategory(categoryModel);
            productModel.setPrice(productDto.getPrice());
            productModel.setProductType(productDto.getProductType());
            productModel.setThumbnail(productDto.getThumbnail());
            productModel.setTitle(productDto.getTitle());

            productRepository.save(productModel);

        }


    }
}
