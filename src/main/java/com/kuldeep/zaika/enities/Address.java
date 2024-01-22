package com.kuldeep.zaika.enities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name="addresses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    User user;

    @NotNull
    private String address;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @Column(length = 6)
    @Pattern(regexp = "[\\s]*[0-9]*[1-9]+",message="msg")
    private String pincode;

    @NotNull
    private double latitude;

    @NotNull
    private double longitude;


}