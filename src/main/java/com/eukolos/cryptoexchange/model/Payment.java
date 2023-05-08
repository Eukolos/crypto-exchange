package com.eukolos.cryptoexchange.model;

import com.eukolos.cryptoexchange.enumaration.EnumCurrency;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
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

}
