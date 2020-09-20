package com.sda.onlinestore.controller;

import com.sda.onlinestore.dto.OrderDto;
import com.sda.onlinestore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("getOrders")
    public ResponseEntity<List<OrderDto>> getOrders() {
        List<OrderDto> orderDtoList = orderService.getAllOrders();
        return new ResponseEntity(orderDtoList, HttpStatus.OK);
    }

    @DeleteMapping("deleteOrder/{id}")
    public ResponseEntity deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("addOrder")
    public ResponseEntity addOrder(@RequestBody OrderDto orderDto) {
        orderService.addOrder(orderDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("getOrder/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable("id") Long id) {
        OrderDto orderDto = orderService.getOrder(id);
        return new ResponseEntity(orderDto, HttpStatus.OK);
    }

    @PutMapping("editOrder")
    public ResponseEntity editOrder(@RequestBody OrderDto orderDto) {
        orderService.updateOrder(orderDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
