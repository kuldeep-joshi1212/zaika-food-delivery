package com.kuldeep.zaika.enities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kuldeep.zaika.enums.CategoryType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Entity
@Table(name="items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @NotNull
    CategoryType categoryType;

    @NotNull
    private String name;

    @NotNull
    int  price;

    @NotNull
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "items")
    private Set<Cart> carts;

    @ManyToOne
    @JoinColumn(name="menu_id",referencedColumnName = "id")
    Menu menu;
}