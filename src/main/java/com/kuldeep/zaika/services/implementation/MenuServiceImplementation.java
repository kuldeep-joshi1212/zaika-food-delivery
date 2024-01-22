package com.kuldeep.zaika.services.implementation;

import com.kuldeep.zaika.enities.Menu;
import com.kuldeep.zaika.enities.dto.MenuDto;
import com.kuldeep.zaika.enities.dto.mapper.MenuDtoMapper;
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
import java.util.Optional;

@Service
public class MenuServiceImplementation implements MenuService {
    private final JwtTokenService jwtTokenService;
    private final MenuRepository menuRepository;
    private final RestaurnatRepository restaurnatRepository;
    private final MenuDtoMapper menuDtoMapper;
    @Autowired
    public MenuServiceImplementation(JwtTokenService jwtTokenService, MenuRepository menuRepository, RestaurnatRepository restaurnatRepository, MenuDtoMapper menuDtoMapper) {
        this.jwtTokenService = jwtTokenService;
        this.menuRepository = menuRepository;
        this.restaurnatRepository = restaurnatRepository;
        this.menuDtoMapper = menuDtoMapper;
    }


    public MenuDto addMenu(Menu menu, Long restaurantID, String username, String token) {
        token=jwtTokenService.convertTokenToString(token);
        if(Boolean.FALSE.equals(jwtTokenService.validateToken(token,username))){
            throw new TokenException("Invalid Token");
        }
        menu.setRestaurant(restaurnatRepository.findRestaurantByid(restaurantID));
        if(Boolean.FALSE.equals(validateMenu(menu))){
            throw new MenuException("Invalid Menu");
        }
        menuRepository.save(menu);
        MenuDto menuDto=menuDtoMapper.toMenuDto(menu);
        return menuDto;
    }
    public MenuDto getMenuByRestaurantId(Long restaurantID,String username,String token){
        token=jwtTokenService.convertTokenToString(token);
        if(Boolean.FALSE.equals(jwtTokenService.validateToken(token,username))){
            throw new TokenException("Invalid Token");
        }
        Long menuId=restaurnatRepository.findRestaurantByid(restaurantID).getId();
        Menu menu = menuRepository.findMenuById(menuId);
        if(Objects.isNull(menu)){
            throw new MenuException("Menu Does Not Exist");
        }
        MenuDto menuDto=menuDtoMapper.toMenuDto(menu);
        return menuDto;
    }

    private boolean validateMenu(Menu menu){
        if(Objects.isNull(menu) ||
            Objects.isNull(menu.getRestaurant())){
            return false;
        }
        return true;
    }
}