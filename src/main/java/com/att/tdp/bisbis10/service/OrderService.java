package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.OrderItemDTO;
import com.att.tdp.bisbis10.dto.OrderRequestDTO;
import com.att.tdp.bisbis10.model.Dish;
import com.att.tdp.bisbis10.model.Order;
import com.att.tdp.bisbis10.model.OrderItem;
import com.att.tdp.bisbis10.model.Restaurant;
import com.att.tdp.bisbis10.repository.OrderItemsRepository;
import com.att.tdp.bisbis10.repository.OrderRepository;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import com.att.tdp.bisbis10.repository.DishRepository;
import com.att.tdp.bisbis10.service.exception.NotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

// Service class to handle order-related operations
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;
    private final OrderItemsRepository orderItemsRepository;

    // Constructor
    @Autowired
    public OrderService(OrderRepository orderRepository, RestaurantRepository restaurantRepository,
                        DishRepository dishRepository, OrderItemsRepository orderItemsRepository) {
        this.orderRepository = orderRepository;
        this.restaurantRepository = restaurantRepository;
        this.dishRepository = dishRepository;
        this.orderItemsRepository = orderItemsRepository;
    }

    // Method to add a new order
    public String addOrder(OrderRequestDTO orderRequestDTO) throws NotFoundException, BadRequestException {

        if (orderRequestDTO.getOrderItemsDto().isEmpty()) {
            throw new BadRequestException("No order items in order request");
        }
        UUID uuid = UUID.randomUUID();
        String stringUuid = uuid.toString();

        Order newOrder = new Order();
        newOrder.setOrderId(stringUuid);
        newOrder = orderRepository.save(newOrder);

        // Check if restaurant exists
        Optional<Restaurant> dbRestaurant = restaurantRepository.findById(orderRequestDTO.getRestaurantId());

        if (dbRestaurant.isPresent()) {
            Restaurant restaurant = dbRestaurant.get();
            newOrder.setRestaurant(restaurant);
        }
        else {
            orderRepository.delete(newOrder);
            throw new NotFoundException("Restaurant not found");
        }

        // Create Order Item entity for each item in the order
        for (OrderItemDTO itemRequest : orderRequestDTO.getOrderItemsDto()) {
            OrderItem orderItem = new OrderItem();

            Optional<Dish> optionalDish = dishRepository.findById(itemRequest.getDishId());

            if (optionalDish.isPresent()) {
                Dish dish = optionalDish.get();

                if (Objects.equals(dish.getRestaurant().getId(), orderRequestDTO.getRestaurantId())) {
                    orderItem.setDish(dish);
                }
                else {
                    orderRepository.delete(newOrder);
                    throw new BadRequestException("Dish can't be ordered from this restaurant");
                }
            }
            else {
                orderRepository.delete(newOrder);
                throw new NotFoundException("Dish not found");
            }

            orderItem.setAmount(itemRequest.getAmount());
            orderItem.setOrder(newOrder);

            newOrder.addOrderItem(orderItem);
            orderItemsRepository.save(orderItem);
        }

        newOrder = orderRepository.save(newOrder);
        return newOrder.getOrderId();

    }

}
