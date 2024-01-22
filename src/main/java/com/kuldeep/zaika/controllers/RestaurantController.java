package com.kuldeep.zaika.controllers;

import com.kuldeep.zaika.enities.Restaurant;
import com.kuldeep.zaika.enities.dto.RestaurantDto;
import com.kuldeep.zaika.exceptions.AuthenticationException;
import com.kuldeep.zaika.exceptions.RestaurantException;
import com.kuldeep.zaika.services.RestaurantService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@Log4j2
public class RestaurantController {
    private final RestaurantService restaurantService;
    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/add")
    ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant,
                                             @RequestParam("username") String username,
                                             @RequestHeader("Authorization") String token){
        try{
            Restaurant savedRestaurant=restaurantService.addRestaurant(restaurant,username,token);
            return ResponseEntity.ok(savedRestaurant);
        }catch(RestaurantException | AuthenticationException e){
            log.error(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get")
    ResponseEntity<RestaurantDto> getRestaurant(@RequestHeader("Authorization") String token,
                                             @RequestParam Long id){
        try {
            RestaurantDto restaurant=restaurantService.getRestaurant(token,id);
            return new ResponseEntity<>(restaurant,HttpStatus.OK);
        }catch (AuthenticationException | RestaurantException e){
            log.error(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getAll")
    ResponseEntity<List<RestaurantDto>> getAllRestaurants(@RequestHeader("Authorization") String token,
                                                          @RequestParam String username){
        try{
            List<RestaurantDto> allRestaurants=restaurantService.getAllRestaurants(token,username);
            return ResponseEntity.ok(allRestaurants);

        } catch (Exception e) {
            log.error(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}