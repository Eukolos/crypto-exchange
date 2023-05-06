package com.eukolos.cryptoexchange.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;

public class PaymentForm {
    @Email
    private String email;
    @Min(4)
    private Integer amount;

    public PaymentForm(String email, Integer amount) {
        this.email = email;
        this.amount = amount;
    }

    public PaymentForm() {
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
