package com.eukolos.cryptoexchange.service;

import com.eukolos.cryptoexchange.dto.AcceptPayment;
import com.eukolos.cryptoexchange.dto.ExchangeRequest;
import com.eukolos.cryptoexchange.dto.LoginRequest;
import com.eukolos.cryptoexchange.dto.UserDto;
import com.eukolos.cryptoexchange.exception.EmailNotFoundException;
import com.eukolos.cryptoexchange.model.*;
import com.eukolos.cryptoexchange.repository.CurrencyRepository;
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

    public UserDto registerUser(LoginRequest request) {
        return new UserDto(repository.save(
                User.builder()
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .accountList(List.of(new Account()))
                        .build()));
    }

    public UserDto findUserByEmail(String email) {
        return new UserDto(repository.findUserByEmail(email).orElseThrow(() -> new EmailNotFoundException("User not found with email: {} " + email)));
    }

    public void acceptPayment(AcceptPayment accepted) {
        User user = repository.findUserByEmail(accepted.email()).orElseThrow(() -> new EmailNotFoundException("User not found with email: {}" + accepted.email()));
        paymentService.savePayment(user, accepted);
        user.getAccountList().get(0).getCurrency().setAmount(user.getAccountList().get(0).getCurrency().getAmount() + accepted.amount());
        log.warn(repository.save(user).toString());
    }

    //authentication
    //
    public User acceptExchange(ExchangeRequest request) {
        User user = repository.findUserByEmail(request.email()).orElseThrow(() -> new EmailNotFoundException("User not found with email: {}" + request.email()));
        Exchange exchange = exchangeService.saveExchange(user, request);

        // Get the first Account object from the user's list
        Account account = user.getAccountList().get(0);

        Currency currency = currencyService.findCurrencyIfExistInAccount(account.getId(), request.coin());
        // Update the amount of the Currency object with the new value
        currency.setAmount(
                Optional.ofNullable(currency.getAmount()).orElse( 0.0f)
                        + exchange.getAmount());
        currency.setType(request.coin());
        currency.setAccount(account);
        currencyService.saveCurrency(currency);
        // !!! GARBAGE ☺☺☺☺
        // Get the list of CryptoCurrencies from the Account object
        // List<Currency> cryptoCurrencies = account.getCryptoCurrencies();
        // POST CALCULATION
        // Find the CryptoCurrency object that matches the request.coin() value
       /* Currency currency = cryptoCurrencies.stream()
                .filter(cur -> cur.getType().equals(request.coin()))
                .findAny()
                .orElse(new Currency());*/


        // Set the updated CryptoCurrency object back into the list
        /*Currency savedCurrency = currencyService.saveCurrency(
                account,
                currency);
        cryptoCurrencies.removeIf(cur -> cur.getType().equals(request.coin()));
        cryptoCurrencies.add(savedCurrency);*/

        // Set the updated CryptoCurrencies list back into the Account object
        /*Account savedAccount = cryptoAccountService.saveCryptoAccount(account);*/

        // Set the updated Account object back into the user's list
       /* user.setAccountList(List.of(savedAccount));*/

        return repository.findUserByEmail(user.getEmail()).orElseThrow(() -> new EmailNotFoundException("User not found with email: {}" + request.email()));
    }


}
