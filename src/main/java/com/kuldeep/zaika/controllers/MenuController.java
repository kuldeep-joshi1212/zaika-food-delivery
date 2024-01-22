package com.kuldeep.zaika.controllers;

import com.kuldeep.zaika.enities.Menu;
import com.kuldeep.zaika.services.MenuService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("menu")
@Log4j2
public class MenuController {
    private final MenuService menuService;
    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/add")
    ResponseEntity<Menu> addMenu(@RequestBody Menu menu,
                                 @RequestHeader String username,
                                 @RequestHeader Long userID,
                                 @RequestHeader("Authorization") String token){
        try {
            Menu savedMenu=menuService.addMenu(menu,userID,username,token);
            return new ResponseEntity<>(menu, HttpStatus.OK);
        }catch(Exception e){
            log.error(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}