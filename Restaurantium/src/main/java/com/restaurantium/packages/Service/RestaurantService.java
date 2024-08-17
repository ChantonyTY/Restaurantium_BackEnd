package com.restaurantium.packages.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurantium.dto.RestaurantUpdateDTO;
import com.restaurantium.packages.Model.Restaurant;
import com.restaurantium.packages.Repository.RestaurantRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> getRestaurantById(String id) {
        return restaurantRepository.findById(id);
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(String pkRestaurant, RestaurantUpdateDTO updateDTO) {
        return restaurantRepository.findById(pkRestaurant)
                .map(existingRestaurant -> {
                    if (updateDTO.getName() != null) {
                        existingRestaurant.setName(updateDTO.getName());
                    }
                    if (updateDTO.getAddress() != null) {
                        existingRestaurant.setAddress(updateDTO.getAddress());
                    }
                    if (updateDTO.getPhoneNumber() != null) {
                        existingRestaurant.setPhone_Number(updateDTO.getPhoneNumber());
                    }
                    return restaurantRepository.save(existingRestaurant);
                })
                .orElse(null);
    }
    public void deleteRestaurant(String id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        restaurantRepository.delete(restaurant);
    }
}