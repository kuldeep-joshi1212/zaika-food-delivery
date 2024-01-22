package com.kuldeep.zaika.enities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kuldeep.zaika.enums.RestaurantType;
import com.kuldeep.zaika.enums.RunningStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="Restaurants")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private String name;

    @NotNull
    private RestaurantType restaurantType;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private double latitude;

    @NotNull
    private double longitude;

    private RunningStatus runningStatus;

    private int rating;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name="restaurant_cuisine",
    joinColumns = @JoinColumn(name="restaurant_id"),
    inverseJoinColumns = @JoinColumn(name = "cuisine_id"))
    private Set<Cuisine> cuisines;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "restaurant")
    Menu menu;
}