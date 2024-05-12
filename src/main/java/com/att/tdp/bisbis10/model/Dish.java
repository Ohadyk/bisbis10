package com.att.tdp.bisbis10.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

/*
This class used to represent the dish entity of the dishes table in the database
* */
@Entity
@Table(name = "dishes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"restaurant_id", "name"})
})
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Float price;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Restaurant restaurant;

    // Getter method for retrieving the dish ID
    public Long getId() {
        return id;
    }

    // Getter method for retrieving the dish name
    public String getName() {
        return name;
    }

    // Getter method for retrieving the dish description
    public String getDescription() {
        return description;
    }

    // Getter method for retrieving the dish price
    public Float getPrice() {
        return price;
    }

    // Getter method for retrieving the dish restaurant
    public Restaurant getRestaurant() {
        return restaurant;
    }

    // Setter method for setting the dish ID
    public void setId(Long id) {
        this.id = id;
    }

    // Setter method for setting the dish name
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    // Setter method for setting the dish description
    public void setDescription(String description) {
        if (description != null && !description.isEmpty()) {
            this.description = description;
        }
    }

    // Setter method for setting the dish price
    public void setPrice(Float price) {
        if (price > 0) {
            this.price = price;
        }
    }

    // Setter method for setting the dish restaurant
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", restaurant=" + restaurant +
                '}';
    }

}
