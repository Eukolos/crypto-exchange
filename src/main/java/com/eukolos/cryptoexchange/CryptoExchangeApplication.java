package com.eukolos.cryptoexchange;

import com.binance.connector.client.impl.SpotClientImpl;
import com.eukolos.cryptoexchange.enumaration.EnumCurrency;
import com.eukolos.cryptoexchange.enumaration.Role;
import com.eukolos.cryptoexchange.model.CryptoAccount;
import com.eukolos.cryptoexchange.model.Currency;
import com.eukolos.cryptoexchange.model.Payment;
import com.eukolos.cryptoexchange.model.User;
import com.eukolos.cryptoexchange.repository.CryptoAccountRepository;
import com.eukolos.cryptoexchange.repository.CurrencyRepository;
import com.eukolos.cryptoexchange.repository.PaymentRepository;
import com.eukolos.cryptoexchange.repository.UserRepository;
import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@SpringBootApplication
@Slf4j
public class CryptoExchangeApplication implements CommandLineRunner {
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;
    private final CryptoAccountRepository cryptoAccountRepository;
    private final PaymentRepository paymentRepository;
    private final String STRIPE_API_KEY;
    private final String BINANCE_API_KEY;
    private final String BINANCE_SECRET_KEY;


    public CryptoExchangeApplication(UserRepository userRepository,
                                     CurrencyRepository currencyRepository,
                                     CryptoAccountRepository cryptoAccountRepository,
                                     PaymentRepository paymentRepository,
                                     @Value("${stripe.api-key}") String stripeApiKey,
                                     @Value("${binance.api-key}") String binanceApiKey,
                                     @Value("${binance.secret-key}") String binanceSecretKey) {
        this.userRepository = userRepository;
        this.currencyRepository = currencyRepository;
        this.cryptoAccountRepository = cryptoAccountRepository;
        this.paymentRepository = paymentRepository;
        STRIPE_API_KEY = stripeApiKey;
        BINANCE_API_KEY = binanceApiKey;
        BINANCE_SECRET_KEY = binanceSecretKey;
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

        log.warn(paymentRepository.save(new Payment()).toString());
//        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();
//        parameters.put("symbol","BTCUSDT");
//        parameters.put("side","SELL");
//        parameters.put("type","MARKET");
//        parameters.put("price",10);
//        SpotClientImpl client = new SpotClientImpl(BINANCE_API_KEY, BINANCE_SECRET_KEY);
//        String result = client.createTrade().testNewOrder(parameters);
//        log.warn(result);

    }
}
