package com.kuldeep.zaika.enities;

import com.kuldeep.zaika.enums.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    private UserType usertype;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(fetch = FetchType.LAZY,mappedBy ="user", cascade = CascadeType.ALL)
    private List<PaymentMethod> addresses;

    @OneToMany(fetch = FetchType.LAZY,mappedBy ="user", cascade = CascadeType.ALL)
    private List<Token> tokens;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user",cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToOne
    @JoinColumn(name="cart_id")
    private Cart cart;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user",cascade = CascadeType.ALL)
    private List<Address> address;

    @OneToOne(mappedBy = "user")
    private Restaurant restaurant;
}