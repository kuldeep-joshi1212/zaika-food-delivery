package com.kuldeep.zaika.services.implementation;

import com.kuldeep.zaika.enities.Restaurant;
import com.kuldeep.zaika.enities.User;
import com.kuldeep.zaika.enums.RestaurantType;
import com.kuldeep.zaika.enums.UserType;
import com.kuldeep.zaika.exceptions.AuthenticationException;
import com.kuldeep.zaika.exceptions.RestaurantException;
import com.kuldeep.zaika.repositories.RestaurnatRepository;
import com.kuldeep.zaika.repositories.UserRepository;
import com.kuldeep.zaika.security.jwt.JwtTokenService;
import com.kuldeep.zaika.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
public class RestaurantServiceImplementation implements RestaurantService {
    private final RestaurnatRepository restaurnatRepository;
    private final UserRepository userRepository;
    private final JwtTokenService jwtTokenService;


    @Autowired
    public RestaurantServiceImplementation(RestaurnatRepository restaurnatRepository,
                                           UserRepository userRepository,
                                           JwtTokenService jwtTokenService) {
        this.restaurnatRepository = restaurnatRepository;
        this.userRepository = userRepository;
        this.jwtTokenService = jwtTokenService;
    }

    public Restaurant addRestaurant(Restaurant restaurant,String username,String token) throws RestaurantException, AuthenticationException {
        if (Boolean.FALSE.equals(validateRestaurant(restaurant))) {
            throw new RestaurantException("invalid restaurant");
        }
        Restaurant idExist = restaurnatRepository.findRestaurantByid(restaurant.getId());
        if (Objects.nonNull(idExist)) {
            throw new RestaurantException("restaurant already exist");
        }
        Restaurant emailExist = restaurnatRepository.findRestaurantByEmail(restaurant.getEmail());
        if (Objects.nonNull(emailExist)) {
            throw new RestaurantException("email exist");
        }
        User user=userRepository.findByUsername(username);
        if(!user.getUsertype().equals(UserType.RESTAURANT)){
            throw new RestaurantException("Incompatible User");
        }
        token=jwtTokenService.convertTokenToString(token);
        if(Boolean.FALSE.equals(jwtTokenService.validateToken(token,username))){
            throw new AuthenticationException("Invalid Token");
        }
        restaurant.setUser(user);
        restaurant.setEmail(user.getEmail());
        restaurant.setPassword(user.getPassword());
        restaurant.setRestaurantType(RestaurantType.VEG);
        Restaurant savedRestaurant=restaurnatRepository.save(restaurant);
        return savedRestaurant;
    }

    public List<Restaurant> getAllRestaurants(String token,String username) throws AuthenticationException {
        token=jwtTokenService.convertTokenToString(token);
        if(Boolean.FALSE.equals(jwtTokenService.validateToken(token,username))){
            throw new AuthenticationException("Invalid Token");
        }
        List<Restaurant> allRestaurants=restaurnatRepository.findAll();
        return allRestaurants;
    }

    public Restaurant getRestaurant(String token, Long id) throws AuthenticationException, RestaurantException {
        token=jwtTokenService.convertTokenToString(token);
        Restaurant restaurant=restaurnatRepository.findRestaurantByid(id);
        if(Objects.isNull(restaurant)){
            throw new RestaurantException("Restaurant does not exist");
        }
        if(Boolean.FALSE.equals(jwtTokenService.validateToken(token, restaurant.getUser().getUsername()))){
            throw new AuthenticationException("Invalid Token");
        }
        return restaurant;
    }


    private boolean validateRestaurant(Restaurant restaurant) throws RestaurantException {
        if (Objects.isNull(restaurant) ||
                Boolean.FALSE.equals(StringUtils.hasText(restaurant.getName())) ||
                Objects.isNull(restaurant.getLatitude()) ||
                Objects.isNull(restaurant.getLongitude())
        ) {
            throw new RestaurantException("Invalid Restaurant");
        }
        return true;
    }
}