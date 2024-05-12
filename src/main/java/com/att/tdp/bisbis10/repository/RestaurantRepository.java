package com.att.tdp.bisbis10.repository;

import com.att.tdp.bisbis10.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
Repository interface for performing CRUD operations on Restaurant entities
* */
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query(value = "SELECT * FROM restaurants WHERE cuisines LIKE CONCAT('%', :cuisine, '%')", nativeQuery = true)
    List<Restaurant> findRestaurantsByCuisine(String cuisine);

    Optional<Restaurant> findByName(String name);

}