package com.eukolos.cryptoexchange.dto;

import com.eukolos.cryptoexchange.model.Payment;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class PaymentDto implements Serializable {
    private final String id;
    private final String paymentId;
    private final float amount;
    private final LocalDate date;

    public PaymentDto(Payment payment) {
        this.id = payment.getId();
        this.paymentId = payment.getPaymentId();
        this.amount = payment.getAmount();
        this.date = payment.getDate();
    }
}