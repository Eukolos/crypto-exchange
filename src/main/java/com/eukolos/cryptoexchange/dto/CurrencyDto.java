package com.eukolos.cryptoexchange.dto;

import com.eukolos.cryptoexchange.enumaration.EnumCurrency;
import com.eukolos.cryptoexchange.model.Currency;
import lombok.Data;

import java.io.Serializable;

@Data
public class CurrencyDto implements Serializable {
    private final String id;
    private final EnumCurrency type;
    private final float amount;

    public CurrencyDto(Currency currency) {
        this.id = currency.getId();
        this.type = currency.getType();
        this.amount = currency.getAmount();
    }
}