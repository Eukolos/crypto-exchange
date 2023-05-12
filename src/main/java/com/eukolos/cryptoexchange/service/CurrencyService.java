package com.eukolos.cryptoexchange.service;

import com.eukolos.cryptoexchange.model.CryptoAccount;
import com.eukolos.cryptoexchange.model.Currency;
import com.eukolos.cryptoexchange.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyRepository repository;

    public Currency saveCurrency(CryptoAccount cryptoAccount, Currency currency){

        return repository.save(
                Currency.builder()
                        .cryptoAccount(cryptoAccount)
                        .type(currency.getType())
                        .amount(currency.getAmount())
                        .build()
        );
    }
}
