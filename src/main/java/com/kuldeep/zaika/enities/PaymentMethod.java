package com.kuldeep.zaika.enities;

import com.kuldeep.zaika.enums.PaymentMethodType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name="payment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private PaymentMethodType paymentMethodType;

    @NotNull
    @ManyToOne
    @JoinColumn(name="user_id" ,referencedColumnName = "id")
    private User user;

    @NotNull
    private String details;
}