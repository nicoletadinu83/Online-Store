package com.sda.onlinestore.controller;

import com.sda.onlinestore.dto.OrderLineDto;
import com.sda.onlinestore.service.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class OrderLineController {

    @Autowired
    private OrderLineService orderLineService;

    @GetMapping("getOrderLines")
    public ResponseEntity<List<OrderLineDto>> getOrderLines() {
        List<OrderLineDto> orderLineDtoList = orderLineService.getOrderLines();
        return new ResponseEntity(orderLineDtoList, HttpStatus.OK);
    }

    @PostMapping("addOrderLine")
    public ResponseEntity addOrderLine(@RequestBody OrderLineDto orderLineDto) {
        orderLineService.addOrderLine(orderLineDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("deleteOrderLine/{id}")
    public ResponseEntity deleteOrderLine(@PathVariable("id") Long id) {
        orderLineService.deleteOrderLine(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
