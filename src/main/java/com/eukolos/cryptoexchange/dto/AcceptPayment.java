package com.eukolos.cryptoexchange.dto;

public record AcceptPayment(
        String paymentId,
        String email,
        float amount
) {
}
