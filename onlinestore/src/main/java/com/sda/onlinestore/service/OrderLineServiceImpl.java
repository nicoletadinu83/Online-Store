package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.AuthorDto;
import com.sda.onlinestore.dto.CategoryDto;
import com.sda.onlinestore.dto.OrderLineDto;
import com.sda.onlinestore.dto.ProductDto;
import com.sda.onlinestore.model.CategoryModel;
import com.sda.onlinestore.model.OrderLineModel;
import com.sda.onlinestore.model.OrderModel;
import com.sda.onlinestore.model.ProductModel;
import com.sda.onlinestore.repository.OrderLineRepository;
import com.sda.onlinestore.repository.OrderRepository;
import com.sda.onlinestore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderLineServiceImpl implements OrderLineService {

    @Autowired
    OrderLineRepository orderLineRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<OrderLineDto> getOrderLines() {
        List<OrderLineDto> orderLineDtoList = new ArrayList<>();
        List<OrderLineModel> orderLineModelList = orderLineRepository.findAll();
        for (OrderLineModel orderLineModel: orderLineModelList){
            OrderLineDto orderLineDto = new OrderLineDto();
            orderLineDto.setId(orderLineModel.getId());

            ProductDto productDto = new ProductDto();
            ProductModel productModel = orderLineModel.getProductModel();
            productDto.setId(productModel.getId());
            productDto.setTitle(productModel.getTitle());
            productDto.setThumbnail(productModel.getThumbnail());

            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(productModel.getCategory().getId());
            categoryDto.setName(productModel.getCategory().getName());
            productDto.setCategory(categoryDto);

            productDto.setPrice(productModel.getPrice());
            productDto.setProductType(productModel.getProductType());

            AuthorDto authorDto = new AuthorDto();
            authorDto.setId(productModel.getAuthor().getId());
            authorDto.setFirstName(productModel.getAuthor().getFirstName());
            authorDto.setLastName(productModel.getAuthor().getLastName());
            productDto.setAuthor(authorDto);
            orderLineDto.setProductDto(productDto);

            orderLineDto.setQuantity(orderLineModel.getQuantity());
            orderLineDto.setPrice(orderLineModel.getProductPrice());
            orderLineDtoList.add(orderLineDto);
        }

        return orderLineDtoList;
    }

    @Override
    public void addOrderLine(OrderLineDto orderLineDto) {
        OrderLineModel orderLineModel = new OrderLineModel();
        Long id = orderLineDto.getProductDto().getId();
        Optional<ProductModel> productModelOptional = productRepository.findById(id);
        if(productModelOptional.isPresent()){
            ProductModel productModel = productModelOptional.get();
            orderLineModel.setProductModel(productModel);
            orderLineModel.setQuantity(1);
            orderLineModel.setProductPrice(productModel.getPrice());
            orderLineModel.setLinePrice((orderLineModel.getProductPrice()*orderLineModel.getQuantity()));
        }
        orderLineRepository.save(orderLineModel);

    }



    @Override
    public void deleteOrderLine(Long id) {
        orderLineRepository.deleteById(id);
    }

    @Override
    public void updateOrderLine(OrderLineDto orderLineDto) {

    }
}
