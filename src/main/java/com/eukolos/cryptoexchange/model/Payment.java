package com.eukolos.cryptoexchange.model;

import com.eukolos.cryptoexchange.enumaration.EnumCurrency;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NaturalId
    private String paymentId;
    private static EnumCurrency currency = EnumCurrency.USD;
    private float amount;
    @CreationTimestamp
    private LocalDate date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name="user_payments",
            joinColumns = @JoinColumn( name="payment_id"),
            inverseJoinColumns = @JoinColumn( name="user_id")
    )
    @ToString.Exclude
    private User user;

}
