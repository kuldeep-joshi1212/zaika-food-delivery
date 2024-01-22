package com.kuldeep.zaika.enities.dto.mapper;

import com.kuldeep.zaika.enities.Menu;
import com.kuldeep.zaika.enities.dto.MenuDto;

public interface MenuDtoMapper {
    MenuDto toMenuDto(Menu menu);
}