package com.att.tdp.bisbis10.model;

import com.att.tdp.bisbis10.converter.StringListConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

/*
This class used to represent the restaurant entity of the restaurants table in the database
* */
@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private double averageRating;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long votes;
    private Boolean isKosher;
    @Convert(converter = StringListConverter.class)
    private List<String> cuisines;

    // Getter method for retrieving the restaurant ID
    public Long getId() {
        return id;
    }

    // Getter method for retrieving the restaurant name
    public String getName() {
        return name;
    }

    // Getter method for retrieving the restaurant average rating
    public Double getAverageRating() {
        return averageRating;
    }

    // Getter method for retrieving the restaurant number of votes
    public Long getVotes() {
        return votes;
    }

    // Getter method for retrieving if the restaurant is kosher
    public Boolean getIsKosher() {
        return isKosher;
    }

    // Getter method for retrieving the restaurant cuisines
    public List<String> getCuisines() {
        return cuisines;
    }

    // Setter method for setting the restaurant ID
    public void setId(Long id) {
        this.id = id;
    }

    // Setter method for setting the restaurant name
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    // Setter method for setting the restaurant average rating
    public void setAverageRating(Double averageRating) {
        if (averageRating > 0 && averageRating <= 5) {
            this.averageRating = averageRating;
        }
    }

    // Setter method for setting the number of votes of the restaurant
    public void setVotes(Long votes) {
        this.votes = votes;
    }

    // Setter method for setting if the restaurant is kosher
    public void setIsKosher(Boolean kosher) {
        isKosher = kosher;
    }

    // Setter method for setting the restaurant cuisines
    public void setCuisines(List<String> cuisines) {
        this.cuisines = cuisines;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", averageRating=" + averageRating +
                ", votes=" + votes +
                ", isKosher=" + isKosher +
                ", cuisines=" + cuisines +
                '}';
    }

}