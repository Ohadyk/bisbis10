package com.att.tdp.bisbis10.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/*
This class used to represent the order entity of the orders table in the database
* */
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderId;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    // Constructor
    public Order() {
        this.orderItems = new ArrayList<OrderItem>();
    }

    // Getter method for retrieving the order ID
    public Long getId() {
        return id;
    }

    // Getter method for retrieving the order uuid
    public String getOrderId() {
        return orderId;
    }

    // Getter method for retrieving the order restaurant
    public Restaurant getRestaurant() {
        return restaurant;
    }

    // Setter method for setting the order ID
    public void setId(Long id) {
        this.id = id;
    }

    // Setter method for setting the order uuid
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    // Setter method for setting the order restaurant
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    // This method gets a order item and adds it to the order items
    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", restaurant=" + restaurant +
                ", orderItems=" + orderItems +
                '}';
    }

}
