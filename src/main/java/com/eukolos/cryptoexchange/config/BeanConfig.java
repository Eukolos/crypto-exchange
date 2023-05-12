package com.eukolos.cryptoexchange.config;

import com.binance.connector.client.SpotClient;
import com.binance.connector.client.impl.SpotClientImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    private final String BINANCE_API_KEY;
    private final String BINANCE_SECRET_KEY;

    public BeanConfig(
            @Value("${binance.api-key}") String binanceApiKey,
            @Value("${binance.secret-key}") String binanceSecretKey) {
        BINANCE_API_KEY = binanceApiKey;
        BINANCE_SECRET_KEY = binanceSecretKey;
    }
    @Bean
    public SpotClient getSpotClient(){
        return new SpotClientImpl(BINANCE_API_KEY, BINANCE_SECRET_KEY);
    }
}
