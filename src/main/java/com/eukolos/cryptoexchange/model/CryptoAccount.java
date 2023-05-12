package com.eukolos.cryptoexchange.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Map;
@NoArgsConstructor
@Setter
@Getter
@ToString
@AllArgsConstructor
@Entity(name = "crypto_accounts")
public class CryptoAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;
    @OneToOne
    private Currency currency;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Currency> cryptoCurrencies;

    public CryptoAccount(Currency currency, List<Currency> cryptoCurrencies) {
        this.currency = currency;
        this.cryptoCurrencies = cryptoCurrencies;
    }
}
