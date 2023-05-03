package com.eukolos.cryptoexchange.model;

import com.eukolos.cryptoexchange.enumaration.Role;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(fetch = FetchType.EAGER)
    private List<CryptoAccount> cryptoAccountList;

}
