package com.eukolos.cryptoexchange.service;

import com.eukolos.cryptoexchange.dto.AcceptPayment;
import com.eukolos.cryptoexchange.dto.ExchangeRequest;
import com.eukolos.cryptoexchange.dto.LoginRequest;
import com.eukolos.cryptoexchange.dto.UserDto;
import com.eukolos.cryptoexchange.exception.EmailNotFoundException;
import com.eukolos.cryptoexchange.model.*;
import com.eukolos.cryptoexchange.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PaymentService paymentService;
    private final ExchangeService exchangeService;
    private final CurrencyService currencyService;
    private final CryptoAccountService cryptoAccountService;

    public UserDto registerUser(LoginRequest request) {
        return new UserDto(repository.save(
                User.builder()
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .cryptoAccountList(List.of(new CryptoAccount()))
                        .build()));
    }

    public UserDto findUserByEmail(String email) {
        return new UserDto(repository.findUserByEmail(email).orElseThrow(() -> new EmailNotFoundException("User not found with email: {} " + email)));
    }

    public void acceptPayment(AcceptPayment accepted) {
        User user = repository.findUserByEmail(accepted.email()).orElseThrow(() -> new EmailNotFoundException("User not found with email: {}" + accepted.email()));
        user.getPaymentList().add(paymentService.savePayment(user, accepted));
        user.getCryptoAccountList().get(0).getCurrency().setAmount(user.getCryptoAccountList().get(0).getCurrency().getAmount() + accepted.amount());
        log.warn(repository.save(user).toString());
    }

    //authentication
    //
    public User acceptExchange(ExchangeRequest request) {
        User user = repository.findUserByEmail(request.email()).orElseThrow(() -> new EmailNotFoundException("User not found with email: {}" + request.email()));
        Exchange exchange = exchangeService.saveExchange(user, request);

        // Get the first CryptoAccount object from the user's list
        CryptoAccount cryptoAccount = user.getCryptoAccountList().get(0);

        // Get the list of CryptoCurrencies from the CryptoAccount object
        List<Currency> cryptoCurrencies = cryptoAccount.getCryptoCurrencies();
        // POST CALCULATION
        // Find the CryptoCurrency object that matches the request.coin() value
        Currency currency = cryptoCurrencies.stream()
                .filter(cur -> cur.getType().equals(request.coin()))
                .findAny()
                .orElse(new Currency());

        // Update the amount of the Currency object with the new value
        currency.setAmount(
                Optional.ofNullable(currency.getAmount()).orElse( 0.0f)
                 + exchange.getAmount());
        currency.setType(request.coin());

        // Set the updated CryptoCurrency object back into the list
        Currency savedCurrency = currencyService.saveCurrency(
                cryptoAccount,
                currency);
        cryptoCurrencies.removeIf(cur -> cur.getType().equals(request.coin()));
        cryptoCurrencies.add(savedCurrency);

        // Set the updated CryptoCurrencies list back into the CryptoAccount object
        CryptoAccount savedCryptoAccount = cryptoAccountService.saveCryptoAccount(cryptoAccount);

        // Set the updated CryptoAccount object back into the user's list
        user.setCryptoAccountList(List.of(savedCryptoAccount));

        return repository.save(user);
        // @Lazy
    }


}
