package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.model.Dish;
import com.att.tdp.bisbis10.service.DishService;
import com.att.tdp.bisbis10.service.exception.NotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{id}/dishes")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping
    public ResponseEntity<Dish> addDish(
            @PathVariable("id") Long restaurantId,
            @RequestBody Dish dish
    ) throws BadRequestException {
        try {
            dishService.addDishToRestaurant(restaurantId, dish);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/{dishId}")
    public ResponseEntity<Dish> updateDish(
            @PathVariable("id") Long restaurant_id,
            @PathVariable("dishId") Long dishId,
            @RequestBody Dish dish
    ) {
        try {
            dishService.updateDish(restaurant_id, dishId, dish);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{dishId}")
    public ResponseEntity<Dish> deleteDish(
            @PathVariable("id") Long restaurantId,
            @PathVariable("dishId") Long dishId
    ) {
        try {
            dishService.deleteDish(restaurantId, dishId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping
    public ResponseEntity<List<Dish>> getDishesByRestaurantId(
            @PathVariable("id") Long restaurant_id
    ) {
        try {
            List<Dish> dishes = dishService.getDishesByRestaurantId(restaurant_id);
            return new ResponseEntity<>(dishes, HttpStatus.OK);
        }
        catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}