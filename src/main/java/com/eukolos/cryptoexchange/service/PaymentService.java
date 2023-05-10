package com.eukolos.cryptoexchange.service;

import com.eukolos.cryptoexchange.model.Payment;
import com.eukolos.cryptoexchange.model.User;
import com.eukolos.cryptoexchange.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository repository;

    public Payment savePayment(User user, Payment payment) {

        return repository.save(Payment.builder()
                .paymentId(payment.getPaymentId())
                .amount(payment.getAmount())
                .user(user)
                .build());
    }

}
