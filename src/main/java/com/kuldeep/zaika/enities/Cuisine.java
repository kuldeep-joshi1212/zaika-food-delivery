package com.kuldeep.zaika.enities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Entity
@Table(name="cuisine")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cuisine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long id;

    @NotNull
    String cuisine;

    @JsonIgnore
    @ManyToMany(mappedBy = "cuisines")
    Set<Restaurant> restaurants;

}