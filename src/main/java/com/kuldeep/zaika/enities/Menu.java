package com.kuldeep.zaika.enities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="menu")
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    Long id;
    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name="restaurant_id")
    Restaurant restaurant;
    // add a relation of foreign key
}