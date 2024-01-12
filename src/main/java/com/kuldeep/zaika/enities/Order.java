package com.kuldeep.zaika.enities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    Long id;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;
    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name="cart_id")
    private Cart cart;
    //add foreign keys
}