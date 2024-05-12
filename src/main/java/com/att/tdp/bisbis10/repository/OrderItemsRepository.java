package com.att.tdp.bisbis10.repository;

import com.att.tdp.bisbis10.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
Repository interface for performing CRUD operations on Order Item entities
* */
@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItem, Long> {}
