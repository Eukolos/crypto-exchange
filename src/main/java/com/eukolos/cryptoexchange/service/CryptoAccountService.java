package com.eukolos.cryptoexchange.service;

import com.eukolos.cryptoexchange.model.CryptoAccount;
import com.eukolos.cryptoexchange.repository.CryptoAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CryptoAccountService {
    private final CryptoAccountRepository repository;

    public CryptoAccount saveCryptoAccount(CryptoAccount cryptoAccount) {
        return repository.save(
                CryptoAccount.builder()
                        .id(cryptoAccount.getId())
                        .cryptoCurrencies(cryptoAccount.getCryptoCurrencies())
                        .currency(cryptoAccount.getCurrency())
                        .user(cryptoAccount.getUser())
                        .build()
        );

    }


}
