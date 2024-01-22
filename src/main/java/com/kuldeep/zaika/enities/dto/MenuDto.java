package com.kuldeep.zaika.enities.dto;

import com.kuldeep.zaika.enities.Item;
import lombok.Data;

import java.util.List;

@Data
public class MenuDto {
    public Long restaurant_id;
    public List<Item> items;
}