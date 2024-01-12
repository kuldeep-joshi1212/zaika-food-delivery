package com.kuldeep.zaika.services.implementation;

import com.kuldeep.zaika.enities.Restaurant;
import com.kuldeep.zaika.exceptions.RestaurantException;
import com.kuldeep.zaika.repositories.RestaurnatRepository;
import com.kuldeep.zaika.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
public class RestaurantServiceImplementation implements RestaurantService {
    private final RestaurnatRepository restaurnatRepository;

    @Autowired
    public RestaurantServiceImplementation(RestaurnatRepository restaurnatRepository) {
        this.restaurnatRepository = restaurnatRepository;
    }

    public Restaurant addRestaurant(Restaurant restaurant) throws RestaurantException {
        if (Boolean.FALSE.equals(validateRestaurant(restaurant))) {
            throw new RestaurantException("invalid restaurant");
        }
        Restaurant idExist = restaurnatRepository.findRestaurantByid(restaurant.getId());
        if (Objects.isNull(idExist)) {
            throw new RestaurantException("restaurant already exist");
        }
        Restaurant emailExist = restaurnatRepository.findRestaurantByid(restaurant.getId());
        if (Objects.isNull(emailExist)) {
            throw new RestaurantException("email exist");
        }
        return restaurant;
    }

    private boolean validateRestaurant(Restaurant restaurant) throws RestaurantException {
        if (Objects.isNull(restaurant) ||
                Boolean.FALSE.equals(StringUtils.hasText(restaurant.getName())) ||
                Boolean.FALSE.equals(StringUtils.hasText(restaurant.getEmail())) ||
                Boolean.FALSE.equals(StringUtils.hasText(restaurant.getPassword())) ||
                Objects.isNull(restaurant.getLatitude()) ||
                Objects.isNull(restaurant.getLongitude())
        ) {
            throw new RestaurantException("invalid restaurant ");
        }
        return true;
    }
}