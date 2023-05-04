package com.eukolos.cryptoexchange;

import com.stripe.Stripe;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class CryptoExchangeApplication {

	@PostConstruct
	public void setup(){
		Dotenv dotenv = Dotenv.configure().load();
		Stripe.apiKey = dotenv.get("STRIPE_API_KEY");
		log.info(Stripe.apiKey);
	}

	public static void main(String[] args) {
		SpringApplication.run(CryptoExchangeApplication.class, args);
	}

}
