package com.att.tdp.bisbis10.dto;

/*
This class used to transfer data from rating request related to restaurant
* */
public class RestaurantDTO {

    // Private fields to store restaurant ID and rating
    private Long restaurantId;
    private double rating;

    // Getter method for retrieving the restaurant ID
    public Long getRestaurantId() {
        return restaurantId;
    }

    // Getter method for retrieving the rating
    public double getRating() {
        return rating;
    }

    // Setter method for setting the restaurant ID
    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    // Setter method for setting the rating
    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "RestaurantDTO{" +
                "restaurantId=" + restaurantId +
                ", rating=" + rating +
                '}';
    }

}
