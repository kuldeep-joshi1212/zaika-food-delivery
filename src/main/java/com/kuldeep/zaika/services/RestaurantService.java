package com.kuldeep.zaika.services;

import com.kuldeep.zaika.enities.Restaurant;
import com.kuldeep.zaika.exceptions.AuthenticationException;
import com.kuldeep.zaika.exceptions.RestaurantException;

import java.util.List;

public interface RestaurantService {
    public Restaurant addRestaurant(Restaurant restaurant,String username,String token) throws RestaurantException, AuthenticationException;

    List<Restaurant> getAllRestaurants(String token,String username) throws AuthenticationException;
    Restaurant getRestaurant(String token,Long id) throws AuthenticationException, RestaurantException;
}