package com.eukolos.cryptoexchange.service;

import com.eukolos.cryptoexchange.dto.AcceptPayment;
import com.eukolos.cryptoexchange.model.Payment;
import com.eukolos.cryptoexchange.model.User;
import com.eukolos.cryptoexchange.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository repository;

    public Payment savePayment(User user, AcceptPayment payment) {

        return repository.save(Payment.builder()
                .paymentId(payment.paymentId())
                .amount(payment.amount())
                .user(user)
                .build());
    }

}
