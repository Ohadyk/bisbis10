package com.att.tdp.bisbis10.repository;

import com.att.tdp.bisbis10.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
Repository interface for performing CRUD operations on Dish entities
* */
@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

    Dish findByRestaurantIdAndName(Long restaurant_id, String dish_name);

    Dish findByRestaurantIdAndId(Long restaurant_id, Long dish_id);

    List<Dish> findAllByRestaurantId(Long restaurant_id);

}
