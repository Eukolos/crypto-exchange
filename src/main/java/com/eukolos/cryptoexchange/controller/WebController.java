package com.eukolos.cryptoexchange.controller;

import com.eukolos.cryptoexchange.dto.PaymentForm;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@Slf4j
public class WebController {
    private final String STRIPE_PUBLIC_KEY;
    public WebController(@Value("${stripe.public-key}") String stripePublicKey) {
        STRIPE_PUBLIC_KEY = stripePublicKey;
    }



    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("paymentForm", new PaymentForm());

        return "index";
    }

    @PostMapping("/")
    public String indexx(@ModelAttribute @Valid PaymentForm paymentForm, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "index";
        }

        model.addAttribute("stripePublicKey", STRIPE_PUBLIC_KEY);
        model.addAttribute("paymentAmount", paymentForm.getAmount());
        model.addAttribute("paymentEmail", paymentForm.getEmail());

        return "payment";
    }



}
