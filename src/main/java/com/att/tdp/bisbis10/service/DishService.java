package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.model.Dish;
import com.att.tdp.bisbis10.model.Restaurant;
import com.att.tdp.bisbis10.repository.DishRepository;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import com.att.tdp.bisbis10.service.exception.NotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Service class to handle dish-related operations
@Service
public class DishService {

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    // Constructor
    @Autowired
    public DishService(DishRepository dishRepository, RestaurantRepository restaurantRepository) {
        this.dishRepository = dishRepository;
        this.restaurantRepository = restaurantRepository;
    }

    // Method to add a new dish to a restaurant
    public void addDishToRestaurant(Long restaurantId, Dish dish) throws NotFoundException, BadRequestException {

        if (dish.getName() == null || dish.getName().isEmpty() ||
                dish.getDescription() == null || dish.getDescription().isEmpty() ||
                dish.getPrice() == null
        ) {
            throw new BadRequestException("Invalid or missing dish parameters");
        }

        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);

        if (restaurant.isEmpty()) {
            throw new NotFoundException("Restaurant not found");
        }
        Restaurant dbRestaurant = restaurant.get();

        Dish dbDish = dishRepository.findByRestaurantIdAndName(dbRestaurant.getId(), dish.getName());

        if (dbDish != null) {
            throw new BadRequestException("Dish already exists in this restaurant");
        }

        Dish newDish = new Dish();

        newDish.setName(dish.getName());
        newDish.setDescription(dish.getDescription());
        newDish.setPrice(dish.getPrice());
        newDish.setRestaurant(dbRestaurant);

        dishRepository.save(newDish);

    }

    // Method to update an existing dish
    public void updateDish(Long restaurant_id, Long dishId, Dish reqDish) throws NotFoundException {

        Dish dbDish = dishRepository.findByRestaurantIdAndId(restaurant_id, dishId);

        if (dbDish == null) {
            throw new NotFoundException("Dish not found");
        }

        if (reqDish.getName() != null) {
            dbDish.setName(reqDish.getName());
        }
        if (reqDish.getDescription() != null) {
            dbDish.setDescription(reqDish.getDescription());
        }
        if (reqDish.getPrice() != null) {
            dbDish.setPrice(reqDish.getPrice());
        }

        dishRepository.save(dbDish);

    }

    // Method to delete a dish from restaurant by restaurant ID and dish ID
    public void deleteDish(Long restaurant_id, Long dishId) throws NotFoundException {

        Dish dbDish = dishRepository.findByRestaurantIdAndId(restaurant_id, dishId);

        if (dbDish == null) {
            throw new NotFoundException("Dish not found in the restaurant");
        }

        dishRepository.deleteById(dishId);
    }

    // Method to retrieve dishes by restaurant ID
    public List<Dish> getDishesByRestaurantId(Long restaurant_id) throws NotFoundException {

        Optional<Restaurant> dbRestaurant = restaurantRepository.findById(restaurant_id);

        if (dbRestaurant.isEmpty()) {
            throw new NotFoundException("Restaurant not found");
        }
        return dishRepository.findAllByRestaurantId(restaurant_id);
    }

}
