package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.*;
import com.sda.onlinestore.model.*;
import com.sda.onlinestore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderDto getOrder(Long id) {
        Optional<OrderModel> orderModelOptional = orderRepository.findById(id);

        if (orderModelOptional.isPresent()) {
            OrderModel orderModel = orderModelOptional.get();

            OrderDto orderDto = new OrderDto();

            orderDto.setId(orderModel.getId());
            orderDto.setTotalCost(orderModel.getTotalCost());
//un order are o lista de OrderLineModel
            List<OrderLineModel> orderLineModelList = orderModel.getOrderLineModel();
            List<OrderLineDto> orderLineDtoList = new ArrayList<>();
            for (OrderLineModel orderLineModel : orderLineModelList) {
                OrderLineDto orderLineDto = new OrderLineDto();
                orderLineDto.setId(orderLineModel.getId());
                orderLineDto.setPrice(orderLineModel.getProductPrice());
                orderLineDto.setQuantity(orderLineModel.getQuantity());

                //un ProductModel are ca membru un AuthorModel un CategoryModel
                ProductModel productModel = orderLineModel.getProductModel();
                ProductDto productDto = new ProductDto();
                productDto.setId(productModel.getId());
                productDto.setPrice(productModel.getPrice());
                productDto.setThumbnail(productModel.getThumbnail());
                productDto.setTitle(productModel.getTitle());
                productDto.setProductType(productModel.getProductType());


                AuthorModel authorModel = productModel.getAuthor();
                AuthorDto authorDto = new AuthorDto();
                authorDto.setId(authorModel.getId());
                authorDto.setFirstName(authorModel.getFirstName());
                authorDto.setLastName(authorModel.getLastName());
                productDto.setAuthor(authorDto);

                ////un ProductModel are ca membru si un CategoryModel
                CategoryModel categoryModel = productModel.getCategory();
                CategoryDto categoryDto = new CategoryDto();
                categoryDto.setId(categoryModel.getId());
                categoryDto.setName(categoryModel.getName());

                //un categoryModel include si membrul parinte de tipul CategoryModel
                CategoryModel parent = categoryModel.getParentCategory();
                CategoryDto parentDto = new CategoryDto();
                parentDto.setId(parent.getId());
                parentDto.setName(parent.getName());
                categoryDto.setParentCategory(parentDto);

                //Fiecare categorie are o lista de subcategorii(de tipul Categorii)
                List<CategoryModel> subcategoryModelList = categoryModel.getSubcategory();
                List<CategoryDto> subcategoryDtoList = new ArrayList<>();
                for (CategoryModel subcategory : subcategoryModelList) {
                    CategoryDto categoryDto1 = new CategoryDto();
                    categoryDto1.setId(subcategory.getId());
                    categoryDto1.setName(subcategory.getName());
                    categoryDto1.setParentCategory(parentDto);
                    subcategoryDtoList.add(categoryDto1);
                }
                categoryDto.setSubcategory(subcategoryDtoList);
//                 productDto.setCategory(categoryDto);
                orderLineDto.setProductDto(productDto);

                orderLineDtoList.add(orderLineDto);
            }

            orderDto.setOrderLineDto(orderLineDtoList);
            orderDto.setOrderDate(orderModel.getOrderDate());
            orderDto.setStatus(orderModel.getStatus());
            orderDto.setTotalCost(orderModel.getTotalCost());

            //Clasa OrderModel are  un delivery adress de tipul UserAdressModel
            UserAdressModel deliveryAdress = orderModel.getDeliveryAddress();
            UserAddressDto deliveryAdressDto = new UserAddressDto();
            deliveryAdressDto.setCity(deliveryAdress.getCity());
            deliveryAdressDto.setCountry(deliveryAdress.getCountry());
            deliveryAdressDto.setId(deliveryAdress.getId());
            deliveryAdressDto.setStreet(deliveryAdress.getStreet());
            deliveryAdressDto.setZipcode(deliveryAdress.getZipcode());

            orderDto.setDeliveryAddress(deliveryAdressDto);

            //Clasa OrderModel are un UserAccountModel userAccountModel(care la randul sau are 2 de UserAdressModel)
            UserAccountModel userAccountModel = orderModel.getUserAccountModel();
            UserAccountDto userAccountDto = new UserAccountDto();
            userAccountDto.setId(userAccountModel.getId());
            userAccountDto.setCity(userAccountModel.getCity());
            userAccountDto.setLogin(userAccountModel.getLogin());
            userAccountDto.setLogotype(userAccountModel.getLogotype());
            userAccountDto.setPassword(userAccountModel.getPassword());
            userAccountDto.setRoletype(userAccountModel.getRoletype());

            UserAdressModel deliveryAdressModel = userAccountModel.getDeliveryAdress();
            UserAddressDto deliveryAdressDto1 = new UserAddressDto();
            deliveryAdressDto1.setZipcode(deliveryAdressModel.getZipcode());
            deliveryAdressDto1.setStreet(deliveryAdressModel.getStreet());
            deliveryAdressDto1.setId(deliveryAdressModel.getId());
            deliveryAdressDto1.setCountry(deliveryAdressModel.getCountry());
            deliveryAdressDto1.setCity(deliveryAdressModel.getCity());
            userAccountDto.setDeliveryAdress(deliveryAdressDto1);


            UserAdressModel userAdressModel = userAccountModel.getUserAdress();
            UserAddressDto userAdressDto = new UserAddressDto();
            userAdressDto.setZipcode(userAdressModel.getZipcode());
            userAdressDto.setStreet(userAdressModel.getStreet());
            userAdressDto.setId(userAdressModel.getId());
            userAdressDto.setCountry(userAdressModel.getCountry());
            userAdressDto.setCity(userAdressModel.getCity());
            userAccountDto.setUserAdress(userAdressDto);

            orderDto.setUserAccountDto(userAccountDto);
            return orderDto;
        }
        return null;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<OrderModel> orderModels = orderRepository.findAll();
        List<OrderDto> orderDtoList = new ArrayList<>();

        for (OrderModel orderModel : orderModels) {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(orderModel.getId());

            UserAdressModel deliveryAdress = orderModel.getDeliveryAddress();
            UserAddressDto deliveryAdressDto = new UserAddressDto();
            deliveryAdressDto.setCity(deliveryAdress.getCity());
            deliveryAdressDto.setCountry(deliveryAdress.getCountry());
            deliveryAdressDto.setId(deliveryAdress.getId());
            deliveryAdressDto.setStreet(deliveryAdress.getStreet());
            deliveryAdressDto.setZipcode(deliveryAdress.getZipcode());
            orderDto.setDeliveryAddress(deliveryAdressDto);
            orderDto.setOrderDate(orderModel.getOrderDate());

            List<OrderLineModel> orderLineModelList = orderModel.getOrderLineModel();
            List<OrderLineDto> orderLineDtoList = new ArrayList<>();
            for (OrderLineModel orderLineModel : orderLineModelList) {
                OrderLineDto orderLineDto = new OrderLineDto();
                orderLineDto.setId(orderLineModel.getId());
                orderLineDto.setPrice(orderLineModel.getProductPrice());
                orderLineDto.setQuantity(orderLineModel.getQuantity());

                ProductModel productModel = orderLineModel.getProductModel();
                ProductDto productDto = new ProductDto();
                productDto.setId(productModel.getId());
                productDto.setPrice(productModel.getPrice());
                productDto.setThumbnail(productModel.getThumbnail());
                productDto.setTitle(productModel.getTitle());
                productDto.setProductType(productModel.getProductType());


                AuthorModel authorModel = productModel.getAuthor();
                AuthorDto authorDto = new AuthorDto();
                authorDto.setId(authorModel.getId());
                authorDto.setFirstName(authorModel.getFirstName());
                authorDto.setLastName(authorModel.getLastName());
                productDto.setAuthor(authorDto);

                CategoryModel categoryModel = productModel.getCategory();
                CategoryDto categoryDto = new CategoryDto();
                categoryDto.setId(categoryModel.getId());
                categoryDto.setName(categoryModel.getName());

                CategoryModel parent = categoryModel.getParentCategory();
                CategoryDto parentDto = new CategoryDto();
                parentDto.setId(parent.getId());
                parentDto.setName(parent.getName());
                categoryDto.setParentCategory(parentDto);

                List<CategoryModel> subcategoryModelList = categoryModel.getSubcategory();
                List<CategoryDto> subcategoryDtoList = new ArrayList<>();
                for (CategoryModel subcategory : subcategoryModelList) {
                    CategoryDto categoryDto1 = new CategoryDto();
                    categoryDto1.setId(subcategory.getId());
                    categoryDto1.setName(subcategory.getName());
                    categoryDto1.setParentCategory(parentDto);
                    subcategoryDtoList.add(categoryDto1);
                }
                categoryDto.setSubcategory(subcategoryDtoList);
//                 productDto.setCategory(categoryDto);
                orderLineDto.setProductDto(productDto);

                orderLineDtoList.add(orderLineDto);
            }

            orderDto.setOrderLineDto(orderLineDtoList);
            orderDto.setOrderDate(orderModel.getOrderDate());
            orderDto.setStatus(orderModel.getStatus());
            orderDto.setTotalCost(orderModel.getTotalCost());

            orderDto.setStatus(orderModel.getStatus());
            orderDto.setTotalCost(orderModel.getTotalCost());

            UserAccountModel userAccountModel = orderModel.getUserAccountModel();
            UserAccountDto userAccountDto = new UserAccountDto();
            userAccountDto.setId(userAccountModel.getId());
            userAccountDto.setCity(userAccountModel.getCity());
            userAccountDto.setLogin(userAccountModel.getLogin());
            userAccountDto.setLogotype(userAccountModel.getLogotype());
            userAccountDto.setPassword(userAccountModel.getPassword());
            userAccountDto.setRoletype(userAccountModel.getRoletype());

            UserAdressModel deliveryAdressModel = userAccountModel.getDeliveryAdress();
            UserAddressDto deliveryAdressDto1 = new UserAddressDto();
            deliveryAdressDto1.setZipcode(deliveryAdressModel.getZipcode());
            deliveryAdressDto1.setStreet(deliveryAdressModel.getStreet());
            deliveryAdressDto1.setId(deliveryAdressModel.getId());
            deliveryAdressDto1.setCountry(deliveryAdressModel.getCountry());
            deliveryAdressDto1.setCity(deliveryAdressModel.getCity());
            userAccountDto.setDeliveryAdress(deliveryAdressDto1);


            UserAdressModel userAdressModel = userAccountModel.getUserAdress();
            UserAddressDto userAdressDto = new UserAddressDto();
            userAdressDto.setZipcode(userAdressModel.getZipcode());
            userAdressDto.setStreet(userAdressModel.getStreet());
            userAdressDto.setId(userAdressModel.getId());
            userAdressDto.setCountry(userAdressModel.getCountry());
            userAdressDto.setCity(userAdressModel.getCity());
            userAccountDto.setUserAdress(userAdressDto);

            orderDto.setUserAccountDto(userAccountDto);

            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void addOrder(OrderDto orderDto) {

        OrderModel orderModel = orderDtoToModel(orderDto);

        orderRepository.save(orderModel);
    }

    @Override
    public void updateOrder(OrderDto orderDto) {
        Optional<OrderModel> foundOrder = orderRepository.findById(orderDto.getId());
        if (foundOrder.isPresent()) {
            OrderModel orderModel = foundOrder.get();

            orderModel.setTotalCost(orderDto.getTotalCost());

            UserAddressDto deliveryAddressDto = orderDto.getDeliveryAddress();
            UserAdressModel deliveryAddress = new UserAdressModel();
            deliveryAddress.setCountry(deliveryAddressDto.getCountry());
            deliveryAddress.setCity(deliveryAddressDto.getCity());
            deliveryAddress.setStreet(deliveryAddressDto.getStreet());
            deliveryAddress.setZipcode(deliveryAddressDto.getZipcode());
            orderModel.setDeliveryAddress(deliveryAddress);

            UserAdressModel userAddress = new UserAdressModel();
            UserAddressDto userAddressDto = orderDto.getUserAddress();
            userAddress.setCountry(userAddressDto.getCountry());
            userAddress.setCity(userAddressDto.getCity());
            userAddress.setStreet(userAddressDto.getStreet());
            userAddress.setZipcode(userAddressDto.getZipcode());
            orderModel.setUserAddress(userAddress);

            orderModel.setOrderDate(orderDto.getOrderDate());

            List<OrderLineDto> orderLineDtoList = orderDto.getOrderLineDto();
            List<OrderLineModel> orderLineModelList = new ArrayList<>();
            OrderLineModel orderLineModel = new OrderLineModel();
            for (OrderLineDto orderLineDto : orderLineDtoList) {
                ProductModel productModel = new ProductModel();
                ProductDto productDto = orderLineDto.getProductDto();
                productModel.setTitle(productDto.getTitle());
                productModel.setThumbnail(productDto.getThumbnail());
                productModel.setCategory(productDto.getCategory());
                productModel.setPrice(productDto.getPrice());
                productModel.setProductType(productDto.getProductType());
                AuthorModel authorModel = new AuthorModel();
                AuthorDto authorDto = productDto.getAuthor();
                authorModel.setId(authorDto.getId());
                authorModel.setFirstName(authorDto.getFirstName());
                authorModel.setLastName(authorDto.getLastName());
                productModel.setAuthor(authorModel);
                orderLineModel.setProductModel(productModel);

                orderLineModel.setQuantity(1);
                orderLineModel.setProductPrice(productModel.getPrice());

                orderLineModelList.add(orderLineModel);
            }

            orderModel.setOrderLineModel(orderLineModelList);

            orderModel.setStatus(orderDto.getStatus());

            UserAccountDto userAccountDto = orderDto.getUserAccountDto();

            UserAccountModel userAccountModel = userAccountDtoToModel(userAccountDto);
            orderModel.setUserAccountModel(userAccountModel);

            orderRepository.save(orderModel);
        }
    }

    private OrderModel orderDtoToModel(OrderDto orderDto) {
        OrderModel orderModel = new OrderModel();
        orderModel.setTotalCost(orderDto.getTotalCost());

        UserAddressDto deliveryAddressDto = orderDto.getDeliveryAddress();
        UserAdressModel deliveryAddress = new UserAdressModel();
        deliveryAddress.setCountry(deliveryAddressDto.getCountry());
        deliveryAddress.setCity(deliveryAddressDto.getCity());
        deliveryAddress.setStreet(deliveryAddressDto.getStreet());
        deliveryAddress.setZipcode(deliveryAddressDto.getZipcode());
        orderModel.setDeliveryAddress(deliveryAddress);

        UserAddressDto userAddressDto = orderDto.getUserAddress();
        UserAdressModel userAddress = new UserAdressModel();
        userAddress.setCountry(userAddressDto.getCountry());
        userAddress.setCity(userAddressDto.getCity());
        userAddress.setStreet(userAddressDto.getStreet());
        userAddress.setZipcode(userAddressDto.getZipcode());
        orderModel.setUserAddress(userAddress);

        orderModel.setOrderDate(orderDto.getOrderDate());

        List<OrderLineDto> orderLineDtoList = orderDto.getOrderLineDto();
        List<OrderLineModel> orderLineModelList = new ArrayList<>();
        OrderLineModel orderLineModel = new OrderLineModel();
        for (OrderLineDto orderLineDto : orderLineDtoList) {
            ProductModel productModel = new ProductModel();
            ProductDto productDto = orderLineDto.getProductDto();
            productModel.setTitle(productDto.getTitle());
            productModel.setThumbnail(productDto.getThumbnail());
            productModel.setCategory(productDto.getCategory());
            productModel.setPrice(productDto.getPrice());
            productModel.setProductType(productDto.getProductType());
            AuthorModel authorModel = new AuthorModel();
            AuthorDto authorDto = productDto.getAuthor();
            authorModel.setId(authorDto.getId());
            authorModel.setFirstName(authorDto.getFirstName());
            authorModel.setLastName(authorDto.getLastName());
            productModel.setAuthor(authorModel);
            orderLineModel.setProductModel(productModel);

            orderLineModel.setQuantity(1);
            orderLineModel.setProductPrice(productModel.getPrice());

            orderLineModelList.add(orderLineModel);
        }

        orderModel.setOrderLineModel(orderLineModelList);

        orderModel.setStatus(orderDto.getStatus());

        UserAccountDto userAccountDto = orderDto.getUserAccountDto();

        UserAccountModel userAccountModel = userAccountDtoToModel(userAccountDto);
        orderModel.setUserAccountModel(userAccountModel);

        return orderModel;
    }

    UserAccountModel userAccountDtoToModel(UserAccountDto userAccountDto){
        UserAccountModel userAccountModel = new UserAccountModel();
        userAccountModel.setId(userAccountDto.getId());
        userAccountModel.setLogin(userAccountDto.getLogin());
        userAccountModel.setPassword(userAccountDto.getPassword());
        userAccountModel.setCity(userAccountDto.getCity());
        UserAdressModel userAddress = userAddressDtoToModel(userAccountDto.getUserAdress());
        userAccountModel.setUserAdress(userAddress);
        UserAdressModel deliveryAddress = deliveryAddressDtoToModel(userAccountDto.getDeliveryAdress());
        userAccountModel.setDeliveryAdress(deliveryAddress);
        userAccountModel.setLogotype(userAccountDto.getLogotype());
        userAccountModel.setRoletype(userAccountDto.getRoletype());
        return userAccountModel;
    }

    UserAdressModel userAddressDtoToModel(UserAddressDto userAddressDto){
        UserAdressModel userAddress = new UserAdressModel();
        userAddress.setCountry(userAddressDto.getCountry());
        userAddress.setCity(userAddressDto.getCity());
        userAddress.setStreet(userAddressDto.getStreet());
        userAddress.setZipcode(userAddressDto.getZipcode());
        return userAddress;
    }

    UserAdressModel deliveryAddressDtoToModel(UserAddressDto deliveryAddressDto){
        UserAdressModel deliveryAddress = new UserAdressModel();
        deliveryAddress.setCountry(deliveryAddressDto.getCountry());
        deliveryAddress.setCity(deliveryAddressDto.getCity());
        deliveryAddress.setStreet(deliveryAddressDto.getStreet());
        deliveryAddress.setZipcode(deliveryAddressDto.getZipcode());
        return deliveryAddress;
    }

}