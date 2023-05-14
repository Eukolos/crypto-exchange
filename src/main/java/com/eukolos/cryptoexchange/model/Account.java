package com.eukolos.cryptoexchange.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@Entity(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name="user_accounts",
            joinColumns = @JoinColumn( name="account_id"),
            inverseJoinColumns = @JoinColumn( name="user_id")
    )
    @ToString.Exclude
    private User user;
    @OneToOne
    private Currency currency;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="account_crypto_currencies",
            joinColumns = @JoinColumn( name="account_id"),
            inverseJoinColumns = @JoinColumn( name="cryptoCurrency_id")
    )
    private List<Currency> cryptoCurrencies;

    public Account(Currency currency, List<Currency> cryptoCurrencies) {
        this.currency = currency;
        this.cryptoCurrencies = cryptoCurrencies;
    }
}
