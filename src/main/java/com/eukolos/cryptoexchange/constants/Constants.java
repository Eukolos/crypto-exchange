package com.eukolos.cryptoexchange.constants;

import org.springframework.beans.factory.annotation.Value;

public class Constants {
    public static String STRIPE_API_KEY;

    @Value("${stripe.api.key}")
    public void setWeatherStackApiBaseUrl(String stripeApiKey) {
        Constants.STRIPE_API_KEY = stripeApiKey;
    }
}
