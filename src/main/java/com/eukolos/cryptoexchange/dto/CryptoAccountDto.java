package com.eukolos.cryptoexchange.dto;

import com.eukolos.cryptoexchange.model.CryptoAccount;
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



    public CryptoAccountDto(CryptoAccount cryptoAccount) {
        this.id = cryptoAccount.getId();
        this.currency =  new CurrencyDto(cryptoAccount.getCurrency());
        List<CurrencyDto> currenciesDto = new ArrayList<>();
        for (Currency currency : cryptoAccount.getCryptoCurrencies()) {
            currenciesDto.add(new CurrencyDto(currency));
        }
        this.cryptoCurrencies = currenciesDto;
    }
}