package com.kuldeep.zaika.enities.dto.mapper;

import com.kuldeep.zaika.enities.Restaurant;
import com.kuldeep.zaika.enities.dto.RestaurantDto;

public interface RestaurantDtoMapper {
    RestaurantDto toRestaurantDto(Restaurant restaurant);
}