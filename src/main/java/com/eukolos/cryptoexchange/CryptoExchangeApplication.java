package com.eukolos.cryptoexchange;

import com.eukolos.cryptoexchange.dto.ExchangeRequest;
import com.eukolos.cryptoexchange.enumaration.EnumCurrency;
import com.eukolos.cryptoexchange.enumaration.Role;
import com.eukolos.cryptoexchange.model.*;
import com.eukolos.cryptoexchange.repository.*;
import com.eukolos.cryptoexchange.service.UserService;
import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.UUID;

@SpringBootApplication
@Slf4j
public class CryptoExchangeApplication implements CommandLineRunner {
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;
    private final ExchangeRepository exchangeRepository;
    private final CryptoAccountRepository cryptoAccountRepository;
    private final PaymentRepository paymentRepository;
    private final UserService userService;
    private final String STRIPE_API_KEY;

    public CryptoExchangeApplication(UserRepository userRepository,
                                     CurrencyRepository currencyRepository,
                                     ExchangeRepository exchangeRepository, CryptoAccountRepository cryptoAccountRepository,
                                     PaymentRepository paymentRepository,
                                     UserService userService, @Value("${stripe.api-key}") String stripeApiKey) {
        this.userRepository = userRepository;
        this.currencyRepository = currencyRepository;
        this.exchangeRepository = exchangeRepository;
        this.cryptoAccountRepository = cryptoAccountRepository;
        this.paymentRepository = paymentRepository;
        this.userService = userService;
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
        User user = userRepository.save(
                User.builder()
                        .email("eminaksoy@gmail.com")
                        .password(UUID.randomUUID().toString())
                        .role(Role.ADMIN)
                        .build()
        );

        Exchange exchange = exchangeRepository.save(
                Exchange.builder()
                        .user(user)
                        .amount(20)
                        .currency(EnumCurrency.ADA)
                        .build()
        );
        cryptoAccountRepository.save(Account.builder()
                        .currency(currencyRepository.save(
                                Currency.builder()
                                        .type(EnumCurrency.USD)
                                        .amount(20)
                                        .build()
                        ))
                        .user(user)
                .build());
        ExchangeRequest exchangeRequest = new ExchangeRequest(
                "eminaksoy@gmail.com",
                EnumCurrency.AVAX,
                10
        );


        userService.acceptExchange(exchangeRequest);

        log.warn(userRepository.findById(user.getId()).toString());




































//        Currency usdCurrency = currencyRepository.save(
//                new Currency(
//                        EnumCurrency.USD,
//                        30
//                )
//        );
//        Currency ethCurrency = currencyRepository.save(
//                new Currency(
//                        EnumCurrency.ETH,
//                        30
//                )
//        );
//        Currency chzCurrency = currencyRepository.save(
//                new Currency(
//                        EnumCurrency.CHZ,
//                        30
//                )
//        );
//
//        Account account = cryptoAccountRepository.save(
//                new Account(
//                        usdCurrency,
//                        Arrays.asList(chzCurrency,ethCurrency)
//                )
//        );
//
//        User user = new User();
//        user.setEmail("eminaksoy@aksoy.com");
//        user.setRole(Role.ADMIN);
//        user.setPassword("123123");
//        user.setAccountList(List.of(account));
//        userRepository.save(
//                user
//        );
//
//        log.warn(user.toString());
//
//        log.warn(paymentRepository.save(Payment.builder()
//                        .user(user)
//                .amount(100).build()).toString());

//        log.warn(userService.acceptExchange(
//                new ExchangeRequest(
//                        "eminaksoy@aksoy.com",
//                        EnumCurrency.BTC,
//                        10
//                )
//
//        ).toString());







    }
}
