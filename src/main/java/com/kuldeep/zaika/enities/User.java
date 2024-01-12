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
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Getter
    @Setter
    @NotNull
    private String username;
    @Getter
    @Setter
    @NotNull
    private String firstname;
    @Getter
    @Setter
    @NotNull
    private String lastname;
    @Getter
    @Setter
    private UserType usertype;
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
    private String token;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @OneToMany(fetch = FetchType.LAZY,mappedBy ="user", cascade = CascadeType.ALL)
    private List<PaymentMethod> addresses;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user",cascade = CascadeType.ALL)
    private List<Order> orders;
    @OneToOne
    @JoinColumn(name="cart_id")
    private Cart cart;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user",cascade = CascadeType.ALL)
    private List<Address> address;
}