package com.kuldeep.zaika.services;

import com.kuldeep.zaika.enities.Menu;

public interface MenuService {
    Menu addMenu(Menu menu,Long userID,String username,String token);
}