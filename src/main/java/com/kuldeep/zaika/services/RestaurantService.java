package com.kuldeep.zaika.services;

import com.kuldeep.zaika.enities.Restaurant;
import com.kuldeep.zaika.enities.dto.RestaurantDto;
import com.kuldeep.zaika.exceptions.AuthenticationException;
import com.kuldeep.zaika.exceptions.RestaurantException;

import java.util.List;

public interface RestaurantService {
    public Restaurant addRestaurant(Restaurant restaurant,String username,String token) throws RestaurantException, AuthenticationException;

    List<RestaurantDto> getAllRestaurants(String token, String username) throws AuthenticationException, RestaurantException;
    RestaurantDto getRestaurant(String token,Long id) throws AuthenticationException, RestaurantException;
}