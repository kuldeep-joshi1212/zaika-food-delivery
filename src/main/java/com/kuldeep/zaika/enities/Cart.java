package com.kuldeep.zaika.enities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name="cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne(mappedBy = "cart")
    User user;

    @OneToOne(mappedBy = "cart")
    Order order;

    @ManyToMany
    @JoinTable(name = "cart_item",
    joinColumns = @JoinColumn(name="cart_id"),
    inverseJoinColumns = @JoinColumn(name="item_id"))
    private Set<Item> items;



}