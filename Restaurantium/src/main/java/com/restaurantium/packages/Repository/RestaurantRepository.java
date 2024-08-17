package com.restaurantium.packages.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurantium.packages.Model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, String> {
}