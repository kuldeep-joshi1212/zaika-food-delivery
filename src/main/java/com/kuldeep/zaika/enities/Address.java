package com.kuldeep.zaika.enities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="addresses")
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Getter
    @Setter
    @NotNull
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    User user;
    @Getter
    @Setter
    @NotNull
    private String address;
    @Getter
    @Setter
    @NotNull
    private String city;
    @Getter
    @Setter
    @NotNull
    private String state;
    @Getter
    @Setter
    @NotNull
    private String pincode;
    @Getter
    @Setter
    @NotNull
    private double latitude;
    @Getter
    @Setter
    @NotNull
    private double longitude;


}