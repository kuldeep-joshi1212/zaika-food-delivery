package com.kuldeep.zaika.enities.dto.mapper.implementation;

import com.kuldeep.zaika.enities.User;
import com.kuldeep.zaika.enities.dto.UserLoginDto;
import com.kuldeep.zaika.enities.dto.mapper.UserLoginDtoMapper;

public class UserLoginDtoMapperImplementation implements UserLoginDtoMapper {
    public UserLoginDto toUserLoginDto(User user) {
        UserLoginDto userLoginDto=new UserLoginDto();
        userLoginDto.setUsername(userLoginDto.getUsername());
        userLoginDto.setPassword(userLoginDto.getPassword());
        return userLoginDto;
    }
}