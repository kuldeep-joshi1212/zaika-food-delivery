package com.kuldeep.zaika.enities;

import com.kuldeep.zaika.enums.PaymentMethodType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="payment")
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Getter
    @Setter
    @NotNull
    private PaymentMethodType paymentMethodType;
    @Getter
    @Setter
    @NotNull
    @ManyToOne
    @JoinColumn(name="user_id" ,referencedColumnName = "id")
    private User user;
    @Getter
    @Setter
    @NotNull
    private String details;
}