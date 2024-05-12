package com.att.tdp.bisbis10.model;

import jakarta.persistence.*;

/*
This class used to represent the order item entity of the order items table in the database
* */
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;

    private int amount;

    // Getter method for retrieving the order item ID
    public Long getId() {
        return id;
    }

    // Getter method for retrieving the order this item ordered from
    public Order getOrder() {
        return order;
    }

    // Getter method for retrieving the dish representing this item
    public Dish getDish() {
        return dish;
    }

    // Getter method for retrieving the amount of this item that ordered
    public int getAmount() {
        return amount;
    }

    // Setter method for setting the order item ID
    public void setId(Long id) {
        this.id = id;
    }

    // Setter method for setting the order this item ordered from
    public void setOrder(Order order) {
        this.order = order;
    }

    // Setter method for setting the dish representing this item
    public void setDish(Dish dish) {
        this.dish = dish;
    }

    // Setter method for setting the amount of this item that ordered
    public void setAmount(int amount) {
        if (amount > 0) {
            this.amount = amount;
        }
    }

    @Override
    public String toString() {
        return "OrderItems{" +
                "id=" + id +
                ", order=" + order +
                ", dish=" + dish +
                ", amount=" + amount +
                '}';
    }

}
