package com.kuldeep.zaika.enities;

import com.kuldeep.zaika.enums.RestaurantType;
import com.kuldeep.zaika.enums.RunningStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="Restaurants")
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Getter
    @Setter
    @NotNull
    private String name;
    @Getter
    @Setter
    @NotNull
    private RestaurantType restaurantType;
    @Getter
    @Setter
    @NotNull
    private String email;
    @Getter
    @Setter
    @NotNull
    private String password;
    @Getter
    @Setter
    @NotNull
    private double latitude;
    @Getter
    @Setter
    @NotNull
    private double longitude;
    @Getter
    @Setter
    private RunningStatus runningStatus;
    @Getter
    @Setter
    private int rating;
    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name="restaurant_cuisine",
    joinColumns = @JoinColumn(name="restaurant_id"),
    inverseJoinColumns = @JoinColumn(name = "cuisine_id"))
    private Set<Cuisine> cuisines;
    //mamytomany maaping restaurant cuisine
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Getter
    @Setter
    @OneToOne(mappedBy = "restaurant")
    Menu menu;
}