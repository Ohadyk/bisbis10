package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.dto.OrderRequestDTO;
import com.att.tdp.bisbis10.service.OrderService;
import com.att.tdp.bisbis10.service.exception.NotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping()
    public ResponseEntity<Map<String, String>> addOrder(
            @RequestBody OrderRequestDTO orderRequestDTO
    ) throws BadRequestException {
        try {
            String orderUuid = orderService.addOrder(orderRequestDTO);

            Map<String, String> response = new HashMap<>();
            response.put("orderId", orderUuid);

            return ResponseEntity.ok().body(response);
        }
        catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
