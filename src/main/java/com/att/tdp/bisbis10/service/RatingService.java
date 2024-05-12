package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.RestaurantDTO;
import com.att.tdp.bisbis10.model.Restaurant;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import com.att.tdp.bisbis10.service.exception.NotFoundException;
import org.apache.coyote.BadRequestException;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Service class to handle rating restaurant operation
@Service
public class RatingService {

    private final RestaurantRepository restaurantRepository;

    // Constructor
    @Autowired
    public RatingService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    // Method to update average rating of a restaurant
    public void addRestaurantRating(RestaurantDTO restaurantDto) throws NotFoundException {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantDto.getRestaurantId());

        if (restaurant.isEmpty()) {
            throw new NotFoundException("Restaurant not found");
        }

        Restaurant dbRestaurant = restaurant.get();

        double avgRating = dbRestaurant.getAverageRating();
        Long numVotes = dbRestaurant.getVotes();
        if (numVotes == null) {
            numVotes = 0L;
        }

        double totalRating = avgRating * numVotes;

        totalRating = (totalRating + restaurantDto.getRating()) / (numVotes + 1);

        double roundedValue = Math.round(totalRating * 100.0) / 100.0;

        dbRestaurant.setAverageRating(roundedValue);
        dbRestaurant.setVotes(numVotes + 1);

        restaurantRepository.save(dbRestaurant);
    }

}
