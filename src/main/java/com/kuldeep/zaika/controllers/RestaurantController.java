package com.kuldeep.zaika.controllers;

import com.kuldeep.zaika.enities.Restaurant;
import com.kuldeep.zaika.services.RestaurantService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
@Log4j2
public class RestaurantController {
    private final RestaurantService restaurantService;
    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }




}