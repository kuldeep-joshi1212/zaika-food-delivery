package com.kuldeep.zaika.services;

import com.kuldeep.zaika.enities.Menu;
import com.kuldeep.zaika.enities.dto.MenuDto;

public interface MenuService {
    MenuDto addMenu(Menu menu, Long userID, String username, String token);
    MenuDto getMenuByRestaurantId(Long restaurantID,String username,String token);
}