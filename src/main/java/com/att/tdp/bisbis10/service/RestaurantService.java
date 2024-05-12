package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.model.Restaurant;
import com.att.tdp.bisbis10.repository.DishRepository;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import com.att.tdp.bisbis10.service.exception.NotFoundException;
import org.apache.coyote.BadRequestException;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Service class to handle restaurant-related operations
@Service
public class RestaurantService {

    // Restaurant repository to interact with the database
    private final RestaurantRepository restaurantRepository;

    // Constructor
    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, DishRepository dishRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    // Method to retrieve all restaurants
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    // Method to retrieve restaurants by cuisine
    public List<Restaurant> getRestaurantsByCuisine(String cuisine) {
        return restaurantRepository.findRestaurantsByCuisine(cuisine);
    }

    // Method to retrieve a restaurant by its ID
    public Restaurant getRestaurantById(Long id) throws NotFoundException {
        Optional<Restaurant> dbRestaurant = restaurantRepository.findById(id);

        if(dbRestaurant.isEmpty()) {
            throw new NotFoundException("Restaurant not found");
        }
        return dbRestaurant.get();
    }

    // Method to add a new restaurant
    public void addRestaurant(Restaurant restaurant) throws BadRequestException {

        if(restaurant.getName() == null || restaurant.getName().isEmpty() || restaurant.getIsKosher() == null ||
                restaurant.getCuisines() == null) {
            throw new BadRequestException("Invalid or missing parameters");
        }

        Optional<Restaurant> dbRestaurant = restaurantRepository.findByName(restaurant.getName());
        if(dbRestaurant.isPresent()) {
            throw new BadRequestException("Restaurant with name " + restaurant.getName() + " already exists");
        }

        restaurantRepository.save(restaurant);
    }

    // Method to update an existing restaurant
    public void updateRestaurant(Long id, Restaurant reqRestaurant) throws BadRequestException {
        Optional<Restaurant> dbRestaurant = restaurantRepository.findById(id);

        if(dbRestaurant.isEmpty()) {
            throw new BadRequestException("Restaurant not found");
        }

        if(reqRestaurant.getName() != null) {
            dbRestaurant.get().setName(reqRestaurant.getName());
        }
        if(reqRestaurant.getIsKosher() != null) {
            dbRestaurant.get().setIsKosher(reqRestaurant.getIsKosher());
        }
        if(reqRestaurant.getCuisines() != null) {
            dbRestaurant.get().setCuisines(reqRestaurant.getCuisines());
        }

        restaurantRepository.save(dbRestaurant.get());
    }

    // Method to delete a restaurant by its ID
    public void deleteRestaurant(Long id) throws NotFoundException {
        Optional<Restaurant> dbRestaurant = restaurantRepository.findById(id);

        if(dbRestaurant.isEmpty()) {
            throw new NotFoundException("Restaurant not found");
        }

        restaurantRepository.deleteById(id);
    }

}