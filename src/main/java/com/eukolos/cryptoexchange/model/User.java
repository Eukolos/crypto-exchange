package com.eukolos.cryptoexchange.model;

import com.eukolos.cryptoexchange.enumaration.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity(name = "users")
@NoArgsConstructor
@Setter
@Getter
@ToString
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(fetch = FetchType.EAGER)
    private List<CryptoAccount> cryptoAccountList;

}
