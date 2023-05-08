package com.eukolos.cryptoexchange;

import com.eukolos.cryptoexchange.enumaration.EnumCurrency;
import com.eukolos.cryptoexchange.enumaration.Role;
import com.eukolos.cryptoexchange.model.CryptoAccount;
import com.eukolos.cryptoexchange.model.Currency;
import com.eukolos.cryptoexchange.model.User;
import com.eukolos.cryptoexchange.repository.CryptoAccountRepository;
import com.eukolos.cryptoexchange.repository.CurrencyRepository;
import com.eukolos.cryptoexchange.repository.UserRepository;
import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@Slf4j
public class CryptoExchangeApplication implements CommandLineRunner {
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;
    private final CryptoAccountRepository cryptoAccountRepository;
    private final String STRIPE_API_KEY;
    public CryptoExchangeApplication(UserRepository userRepository, CurrencyRepository currencyRepository, CryptoAccountRepository cryptoAccountRepository, @Value("${stripe.api-key}") String stripeApiKey) {
        this.userRepository = userRepository;
        this.currencyRepository = currencyRepository;
        this.cryptoAccountRepository = cryptoAccountRepository;
        STRIPE_API_KEY = stripeApiKey;
    }

    @PostConstruct
    public void setup() {
        Stripe.apiKey = STRIPE_API_KEY;
    }
    public static void main(String[] args) {
        SpringApplication.run(CryptoExchangeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Currency usdCurrency = currencyRepository.save(
                new Currency(
                        EnumCurrency.USD,
                        30
                )
        );
        Currency ethCurrency = currencyRepository.save(
                new Currency(
                        EnumCurrency.ETH,
                        30
                )
        );
        Currency chzCurrency = currencyRepository.save(
                new Currency(
                        EnumCurrency.CHZ,
                        30
                )
        );

        CryptoAccount cryptoAccount = cryptoAccountRepository.save(
                new CryptoAccount(
                        usdCurrency,
                        Arrays.asList(chzCurrency,ethCurrency)
                )
        );

        User user = new User();
        user.setEmail("eminaksoy@aksoy.com");
        user.setRole(Role.ADMIN);
        user.setPassword("123123");
        user.setCryptoAccountList(List.of(cryptoAccount));
        userRepository.save(
                user
        );

        log.warn(user.toString());


    }
}
