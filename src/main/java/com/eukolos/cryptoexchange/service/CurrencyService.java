package com.eukolos.cryptoexchange.service;

import com.eukolos.cryptoexchange.enumaration.EnumCurrency;
import com.eukolos.cryptoexchange.model.Account;
import com.eukolos.cryptoexchange.model.Currency;
import com.eukolos.cryptoexchange.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyRepository repository;

    public Currency saveCurrency(Currency currency){

        return repository.save(
                Currency.builder()
                        .id(currency.getId())
                        .account(currency.getAccount())
                        .type(currency.getType())
                        .amount(currency.getAmount())
                        .build()
        );
    }
    public Currency findCurrencyIfExistInAccount(String accountId, EnumCurrency type){
        return repository.findByAccount_IdAndType(accountId, type).orElse(new Currency());
    }
}
