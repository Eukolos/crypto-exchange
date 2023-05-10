package com.eukolos.cryptoexchange.controller;

import com.eukolos.cryptoexchange.dto.CreatePayment;
import com.eukolos.cryptoexchange.dto.CreatePaymentResponse;
import com.eukolos.cryptoexchange.dto.UserDto;
import com.eukolos.cryptoexchange.service.UserService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final UserService userService;

    @PostMapping("/create-payment-intent")
    public CreatePaymentResponse createPaymentIntent(@RequestBody @Valid CreatePayment createPayment) throws StripeException {
        UserDto userDto = userService.findUserByEmail(createPayment.getEmail());
        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setReceiptEmail(userDto.getEmail())
                .setCurrency("usd")
                .setAmount(createPayment.getAmount() * 100L)
                .build();
        PaymentIntent intent = PaymentIntent.create(createParams);
        return new CreatePaymentResponse(intent.getClientSecret());
    }
}
