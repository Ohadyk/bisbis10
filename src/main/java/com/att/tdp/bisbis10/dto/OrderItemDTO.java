package com.att.tdp.bisbis10.dto;

/*
This class used to transfer data from order request related to the order items
* */
public class OrderItemDTO {

    // Private fields to store dish ID and amount ordered
    private Long dishId;
    private int amount;

    // Getter method for retrieving the dish ID
    public Long getDishId() {
        return dishId;
    }

    // Getter method for retrieving the amount
    public int getAmount() {
        return amount;
    }

    // Setter method for setting the dish ID
    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    // Setter method for setting the amount
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "OrderItemDTO{" +
                "dishId=" + dishId +
                ", amount=" + amount +
                '}';
    }

}
