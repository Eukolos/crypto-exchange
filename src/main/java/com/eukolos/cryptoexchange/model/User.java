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
    @JoinTable(
            name="user_accounts",
            joinColumns = @JoinColumn( name="user_id"),
            inverseJoinColumns = @JoinColumn( name="account_id")
    )
    private List<Account> accountList;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="user_payments",
            joinColumns = @JoinColumn( name="user_id"),
            inverseJoinColumns = @JoinColumn( name="payment_id")
    )
    private List<Payment> paymentList;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="user_exchanges",
            joinColumns = @JoinColumn( name="user_id"),
            inverseJoinColumns = @JoinColumn( name="exchange_id")
    )
    private List<Exchange> exchangeList;


}
