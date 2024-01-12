package com.kuldeep.zaika.services;

import com.kuldeep.zaika.enities.Restaurant;
import com.kuldeep.zaika.exceptions.RestaurantException;

public interface RestaurantService {
    public Restaurant addRestaurant(Restaurant restaurant) throws RestaurantException;
}