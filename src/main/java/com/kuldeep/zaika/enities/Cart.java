package com.kuldeep.zaika.enities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="cart")
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    Long id;
    @Getter
    @Setter
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