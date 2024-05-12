package com.att.tdp.bisbis10.dto;

import java.util.List;

/*
This class used to transfer data from order request related to restaurant and order items
* */
public class OrderRequestDTO {

    // Private fields to store restaurant ID and order items
    private Long restaurantId;
    private List<OrderItemDTO> orderItemsDto;

    // Getter method for retrieving the restaurant ID
    public Long getRestaurantId() {
        return restaurantId;
    }

    // Getter method for retrieving the order items
    public List<OrderItemDTO> getOrderItemsDto() {
        return orderItemsDto;
    }

    // Setter method for setting the restaurant ID
    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    // Setter method for setting the order items
    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItemsDto = orderItems;
    }

    @Override
    public String toString() {
        return "OrderRequestDTO{" +
                "restaurantId=" + restaurantId +
                ", orderItemsDto=" + orderItemsDto +
                '}';
    }

}
