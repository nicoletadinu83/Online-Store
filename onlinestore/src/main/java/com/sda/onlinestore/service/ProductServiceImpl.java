package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.ProductDto;
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
    @Override
    public ProductDto getProduct(Long id) {
        Optional<ProductModel> foundProductModel = productRepository.findById(id);
        if (foundProductModel.isPresent()) {
            ProductModel productModel= foundProductModel.get();
            ProductDto productDto= new ProductDto();

            productDto.setAuthor(productModel.getAuthor());
            productDto.setCategory(productModel.getCategory());
            productDto.setId(productModel.getId());
            productDto.setPrice(productModel.getPrice());
            productDto.setProductType(productModel.getProductType());
            productDto.setThumbnail(productModel.getThumbnail());
            productDto.setTitle(productModel.getTitle());

            return productDto;
        }
        return null;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<ProductModel> productModelList = productRepository.findAll();
        List<ProductDto> productDtoList= new ArrayList<>();

        for(ProductModel productModel: productModelList){
            ProductDto productDto= new ProductDto();
            productDto.setId(productModel.getId());
            productDto.setTitle(productModel.getTitle());
            productDto.setProductType(productModel.getProductType());
            productDto.setThumbnail(productModel.getThumbnail());
            productDto.setPrice(productModel.getPrice());
            productDto.setCategory(productModel.getCategory());
            productDto.setAuthor(productModel.getAuthor());

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
        ProductModel productModel= new ProductModel();
        productModel.setId(productDto.getId());
        productModel.setAuthor(productDto.getAuthor());
        productModel.setCategory(productDto.getCategory());
        productModel.setPrice(productDto.getPrice());
        productModel.setProductType(productDto.getProductType());
        productModel.setThumbnail(productDto.getThumbnail());
        productModel.setTitle(productDto.getTitle());

         productRepository.save(productModel);

    }

    @Override
    public void updateProduct(ProductDto productDto) {
        Optional<ProductModel> productModelFind = productRepository.findById(productDto.getId());
        if(productModelFind.isPresent()){
            ProductModel productModel=productModelFind.get();
            productModel.setId(productDto.getId());
            productModel.setAuthor(productDto.getAuthor());
            productModel.setCategory(productDto.getCategory());
            productModel.setPrice(productDto.getPrice());
            productModel.setProductType(productDto.getProductType());
            productModel.setThumbnail(productDto.getThumbnail());
            productModel.setTitle(productDto.getTitle());

            productRepository.save(productModel);

        }


    }
}
