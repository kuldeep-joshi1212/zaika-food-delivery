package com.kuldeep.zaika.enities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long id;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name="cart_id")
    private Cart cart;
    //add foreign keys
}