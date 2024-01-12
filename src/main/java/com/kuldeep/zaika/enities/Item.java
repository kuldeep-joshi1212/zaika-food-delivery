package com.kuldeep.zaika.enities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kuldeep.zaika.enums.CategoryType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="items")
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    Long id;
    @Getter
    @Setter
    Long menu_id;//add foreign key
    @Getter
    @Setter
    @NotNull
    CategoryType categoryType;
    @Getter
    @Setter
    @NotNull
    private String name;
    @Getter
    @Setter
    @NotNull
    int  price;
    @Getter
    @Setter
    @NotNull
    private String description;
    @JsonIgnore
    @ManyToMany(mappedBy = "items")
    private Set<Cart> carts;
}