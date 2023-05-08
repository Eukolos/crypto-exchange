package com.eukolos.cryptoexchange.model;

import com.eukolos.cryptoexchange.enumaration.EnumCurrency;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
@AllArgsConstructor
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private EnumCurrency type;
    private float amount;

    public Currency(EnumCurrency type, float amount) {
        this.type = type;
        this.amount = amount;
    }
}
