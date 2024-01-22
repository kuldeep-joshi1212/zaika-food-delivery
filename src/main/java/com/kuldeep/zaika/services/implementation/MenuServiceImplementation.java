package com.kuldeep.zaika.services.implementation;

import com.kuldeep.zaika.enities.Menu;
import com.kuldeep.zaika.exceptions.MenuException;
import com.kuldeep.zaika.exceptions.TokenException;
import com.kuldeep.zaika.repositories.MenuRepository;
import com.kuldeep.zaika.repositories.RestaurnatRepository;
import com.kuldeep.zaika.repositories.UserRepository;
import com.kuldeep.zaika.security.jwt.JwtTokenService;
import com.kuldeep.zaika.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Service
public class MenuServiceImplementation implements MenuService {
    private final JwtTokenService jwtTokenService;
    private final MenuRepository menuRepository;
    private final UserRepository userRepository;
    private final RestaurnatRepository restaurnatRepository;
    @Autowired
    public MenuServiceImplementation(JwtTokenService jwtTokenService, MenuRepository menuRepository, UserRepository userRepository, RestaurnatRepository restaurnatRepository) {
        this.jwtTokenService = jwtTokenService;
        this.menuRepository = menuRepository;
        this.userRepository = userRepository;
        this.restaurnatRepository = restaurnatRepository;
    }


    public Menu addMenu(Menu menu, Long userId, String username, String token) {
        token=jwtTokenService.convertTokenToString(token);
        if(Boolean.FALSE.equals(jwtTokenService.validateToken(token,username))){
            throw new TokenException("Invalid Token");
        }
        menu.setRestaurant(restaurnatRepository.findRestaurantByid(userId));
        if(Boolean.FALSE.equals(validateMenu(menu))){
            throw new MenuException("Invalid Menu");
        }
        menuRepository.save(menu);
        return menu;


    }
    private boolean validateMenu(Menu menu){
        if(Objects.isNull(menu) ||
            Objects.isNull(menu.getRestaurant())){
            return false;
        }
        return true;
    }
}