package com.eukolos.cryptoexchange.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;

import lombok.*;

public class CreatePayment {
    @Min(4)
    private Integer amount;

    @Email
    private String email;

    public CreatePayment() {
    }

    public CreatePayment(Integer amount, String email) {
        this.amount = amount;
        this.email = email;
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
