package com.eukolos.cryptoexchange.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
@Entity
@NoArgsConstructor
public class CryptoAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Currency> currencies;
}
