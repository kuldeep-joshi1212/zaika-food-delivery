package com.kuldeep.zaika.enities.dto;

import com.kuldeep.zaika.enities.Cuisine;
import com.kuldeep.zaika.enities.Menu;
import com.kuldeep.zaika.enums.RestaurantType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class RestaurantDto {
    Long id;
    String name;
    RestaurantType restaurantType;
    double latitude;
    double longitude;
    int rating;
    Set<String> cuisines;
    Menu menu;



}