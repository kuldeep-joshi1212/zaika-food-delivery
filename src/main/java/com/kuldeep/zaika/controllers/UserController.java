package com.kuldeep.zaika.controllers;

import com.kuldeep.zaika.enities.User;
import com.kuldeep.zaika.exceptions.UserException;
import com.kuldeep.zaika.repositories.UserRepository;
import com.kuldeep.zaika.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@Log4j2
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping ("/signup")
    ResponseEntity<User> signUp(@RequestBody User user){
        try{
            User signedUpUser= userService.signUp(user);
            if(Objects.nonNull(signedUpUser)){
                return ResponseEntity.ok(signedUpUser);
            }
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("login")
    ResponseEntity<User> login(@RequestBody User user){
        try{
            User loggedInUser=userService.login(user);
            if(Objects.nonNull(loggedInUser)){
                return ResponseEntity.ok(loggedInUser);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(UserException e){
            log.error("please enter"+e);
            return new ResponseEntity<>(HttpStatus.IM_USED);
        }
    }
    @GetMapping("/get")
    ResponseEntity<User> getUserById(@RequestParam Long id)throws UserException{
        try{
            return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
        }catch(UserException e){
            log.error("user not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}