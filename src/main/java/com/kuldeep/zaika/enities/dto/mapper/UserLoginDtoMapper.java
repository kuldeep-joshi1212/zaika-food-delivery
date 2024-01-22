package com.kuldeep.zaika.enities.dto.mapper;

import com.kuldeep.zaika.enities.User;
import com.kuldeep.zaika.enities.dto.UserLoginDto;

public interface UserLoginDtoMapper {
    UserLoginDto toUserLoginDto(User user);
}