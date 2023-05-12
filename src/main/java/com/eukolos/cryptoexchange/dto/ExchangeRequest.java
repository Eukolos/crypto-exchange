package com.eukolos.cryptoexchange.dto;

import com.eukolos.cryptoexchange.enumaration.EnumCurrency;

public record ExchangeRequest(
        String email,
        EnumCurrency coin,
        float amount
) {
}
