package com.eukolos.cryptoexchange.service;

import com.eukolos.cryptoexchange.model.Account;
import com.eukolos.cryptoexchange.repository.CryptoAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CryptoAccountService {
    private final CryptoAccountRepository repository;

    public Account saveCryptoAccount(Account account) {
        return repository.save(
                Account.builder()
                        .id(account.getId())
                        .cryptoCurrencies(account.getCryptoCurrencies())
                        .currency(account.getCurrency())
                        .user(account.getUser())
                        .build()
        );

    }


}
