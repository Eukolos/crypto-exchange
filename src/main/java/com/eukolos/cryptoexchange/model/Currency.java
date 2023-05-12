package com.eukolos.cryptoexchange.model;

import com.eukolos.cryptoexchange.enumaration.EnumCurrency;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Builder
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crypto_account_id")
    @ToString.Exclude
    private CryptoAccount cryptoAccount;

    public Currency(EnumCurrency type, float amount) {
        this.type = type;
        this.amount = amount;
    }
}
