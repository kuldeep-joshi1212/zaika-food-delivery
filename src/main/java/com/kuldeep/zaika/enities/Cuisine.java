package com.kuldeep.zaika.enities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="cuisine")
@AllArgsConstructor
@NoArgsConstructor
public class Cuisine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    Long id;
    @Getter
    @Setter
    @NotNull
    String cuisine;
    @Getter
    @Setter
    @ManyToMany(mappedBy = "cuisines")
    Set<Restaurant> restaurants;

}