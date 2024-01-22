package com.kuldeep.zaika.enities.dto.mapper.implementation;

import com.kuldeep.zaika.enities.Item;
import com.kuldeep.zaika.enities.Menu;
import com.kuldeep.zaika.enities.dto.MenuDto;
import com.kuldeep.zaika.enities.dto.mapper.MenuDtoMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class MenuDtoMapperImplementation implements MenuDtoMapper {
    public MenuDto toMenuDto(Menu menu) {
        MenuDto menuDto=new MenuDto();
        menuDto.setRestaurant_id(menu.getId());
        List<Item> items=new ArrayList<>();
        for(Item item: menu.getItems()){
            items.add(item);
        }
        menuDto.setItems(items);
        return menuDto;
    }
}