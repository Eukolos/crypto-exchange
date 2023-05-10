package com.eukolos.cryptoexchange.service;

import com.eukolos.cryptoexchange.dto.AcceptPayment;
import com.eukolos.cryptoexchange.dto.LoginRequest;
import com.eukolos.cryptoexchange.dto.UserDto;
import com.eukolos.cryptoexchange.exception.EmailNotFoundException;
import com.eukolos.cryptoexchange.model.CryptoAccount;
import com.eukolos.cryptoexchange.model.Payment;
import com.eukolos.cryptoexchange.model.User;
import com.eukolos.cryptoexchange.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PaymentService paymentService;

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
        Payment payment = paymentService.savePayment(user, accepted);
        List<Payment> paymentList = user.getPaymentList();
        paymentList.add(payment);
        user.setPaymentList(paymentList);
        float total = user.getCryptoAccountList().get(0).getCurrency().getAmount() + accepted.amount();
        user.getCryptoAccountList().get(0).getCurrency().setAmount(total);
        log.warn(repository.save(user).toString());
    }

}
