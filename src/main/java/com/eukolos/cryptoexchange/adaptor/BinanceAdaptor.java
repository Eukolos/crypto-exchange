package com.eukolos.cryptoexchange.adaptor;

import com.binance.connector.client.SpotClient;
import com.eukolos.cryptoexchange.dto.Quote;
import com.eukolos.cryptoexchange.enumaration.EnumCurrency;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
@Slf4j
@Component
@RequiredArgsConstructor
public class BinanceAdaptor {
    private final SpotClient spotClient;


    public Quote quote(EnumCurrency currency){
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<String, Object>();
        parameters.put("quoteAsset",String.valueOf(currency));
        parameters.put("baseAsset","USDT");
        parameters.put("quoteQty",1L);

        String result = spotClient.createBswap().quote(parameters);
        Quote quote = new Gson().fromJson(result, Quote.class);
        log.warn(result);
        return quote;
    }
}
