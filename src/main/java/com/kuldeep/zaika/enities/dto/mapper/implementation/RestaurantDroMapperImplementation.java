package com.kuldeep.zaika.enities.dto.mapper.implementation;

import com.kuldeep.zaika.enities.Cuisine;
import com.kuldeep.zaika.enities.Restaurant;
import com.kuldeep.zaika.enities.dto.RestaurantDto;
import com.kuldeep.zaika.enities.dto.mapper.RestaurantDtoMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
@Component
public class RestaurantDroMapperImplementation implements RestaurantDtoMapper {
    public RestaurantDto toRestaurantDto(Restaurant restaurant) {
        RestaurantDto restaurantDto=new RestaurantDto();
        restaurantDto.setId(restaurant.getId());
        restaurantDto.setName(restaurantDto.getName());
        restaurantDto.setRestaurantType(restaurant.getRestaurantType());
        restaurantDto.setLatitude(restaurantDto.getLatitude());
        restaurantDto.setLongitude(restaurantDto.getLongitude());
        restaurantDto.setRating(restaurantDto.getRating());
        restaurantDto.setMenu(restaurant.getMenu());
        Set<String> cuisines=new HashSet<>();
        for(Cuisine cuisine:restaurant.getCuisines()){
            cuisines.add(cuisine.getCuisine());
        }
        restaurantDto.setCuisines(cuisines);
        return restaurantDto;
    }
}