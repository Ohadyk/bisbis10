package com.att.tdp.bisbis10.repository;

import com.att.tdp.bisbis10.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
Repository interface for performing CRUD operations on Order entities
* */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {}
