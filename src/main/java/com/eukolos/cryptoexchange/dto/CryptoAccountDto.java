package com.eukolos.cryptoexchange.dto;

import com.eukolos.cryptoexchange.model.Account;
import com.eukolos.cryptoexchange.model.Currency;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class CryptoAccountDto implements Serializable {
    private final String id;
    private final CurrencyDto currency;
    private final List<CurrencyDto> cryptoCurrencies;



    public CryptoAccountDto(Account account) {
        this.id = account.getId();
        this.currency =  new CurrencyDto(account.getCurrency());
        List<CurrencyDto> currenciesDto = new ArrayList<>();
        for (Currency currency : account.getCryptoCurrencies()) {
            currenciesDto.add(new CurrencyDto(currency));
        }
        this.cryptoCurrencies = currenciesDto;
    }
}