package com.eukolos.cryptoexchange.enumaration;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CurrencyConverter implements Converter<String, EnumCurrency> {
    @Override
    public EnumCurrency convert(String source) {
        return EnumCurrency.valueOf(source.toUpperCase());
    }
}