package com.eukolos.cryptoexchange;

import com.eukolos.cryptoexchange.constants.Constants;
import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CryptoExchangeApplication {
	@PostConstruct
	public void setup(){
		Stripe.apiKey = Constants.STRIPE_API_KEY;
	}

	public static void main(String[] args) {
		SpringApplication.run(CryptoExchangeApplication.class, args);
	}

}
