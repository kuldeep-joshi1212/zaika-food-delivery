package com.kuldeep.zaika.enities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name="menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    @JoinColumn(name="restaurant_id")
    Restaurant restaurant;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "menu",cascade = CascadeType.ALL)
    Set<Item> items;
}