package com.kuldeep.zaika.services;

import com.kuldeep.zaika.enities.Token;
import com.kuldeep.zaika.enities.User;
import com.kuldeep.zaika.enities.dto.UserLoginDto;
import com.kuldeep.zaika.exceptions.UserException;
import org.springframework.stereotype.Service;

public interface UserService {
    User signUp(User user) throws UserException;
    User getUserById(Long id) throws UserException;
    String login(UserLoginDto userLoginDto) throws UserException;
}