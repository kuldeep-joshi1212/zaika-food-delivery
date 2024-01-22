package com.kuldeep.zaika.enities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tokens")
@Data
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    private User user;

    @Column(name = "user_id")
    private long userID;

    private String token;
}