package com.eukolos.cryptoexchange.model;

import com.eukolos.cryptoexchange.enumaration.EnumCurrency;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private EnumCurrency type;
    private float amount;
}
