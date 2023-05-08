package com.eukolos.cryptoexchange;

import com.stripe.Stripe;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class CryptoExchangeApplication {
    private final String STRIPE_API_KEY;
    public CryptoExchangeApplication(@Value("${stripe.api-key}") String stripeApiKey) {
        STRIPE_API_KEY = stripeApiKey;
    }

    @PostConstruct
    public void setup() {
        Stripe.apiKey = STRIPE_API_KEY;
    }
    public static void main(String[] args) {
        SpringApplication.run(CryptoExchangeApplication.class, args);
    }

}
