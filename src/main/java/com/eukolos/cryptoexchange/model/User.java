package com.eukolos.cryptoexchange.model;

import com.eukolos.cryptoexchange.enumaration.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(fetch = FetchType.EAGER)
    private List<CryptoAccount> cryptoAccountList;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Payment> paymentList;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Exchange> exchangeList;
    public List<CryptoAccount> addCryptoAccount(CryptoAccount cryptoAccount) {
        cryptoAccountList.add(cryptoAccount);
        return cryptoAccountList;
    }

}
