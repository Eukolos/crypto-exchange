package com.eukolos.cryptoexchange.controller;

import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.*;
import com.stripe.net.Webhook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class StripeWebHookController {
    private final String STRIPE_ENDPOINT_SECRET;

    public StripeWebHookController(@Value("${stripe.endpoint-secret}") String stripeEndpointSecret) {
        STRIPE_ENDPOINT_SECRET = stripeEndpointSecret;
    }


    @PostMapping("/stripe/events")
    public String handleStripeEvent(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) {

        // If you are testing with the CLI, find the secret by running 'stripe listen'
        // If you are using an endpoint defined with the API or dashboard, look in your webhook settings
        // at https://dashboard.stripe.com/webhooks
        if (sigHeader == null) {
            return "";
        }

        Event event;
        // Only verify the event if you have an endpoint secret defined.
        // Otherwise use the basic event deserialized with GSON.
        try {
            event = Webhook.constructEvent(
                    payload, sigHeader, STRIPE_ENDPOINT_SECRET
            );
        } catch (SignatureVerificationException e) {
            // Invalid signature
            log.warn("⚠️  Webhook error while validating signature.");
            return "";
        }
        // Deserialize the nested object inside the event
        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        StripeObject stripeObject = dataObjectDeserializer.getObject().orElseThrow();
        // Handle the event
        switch (event.getType()) {
            case "payment_intent.succeeded" -> {
                PaymentIntent paymentIntent = (PaymentIntent) stripeObject;
                log.info(paymentIntent.getId());
                log.info(paymentIntent.getReceiptEmail());
                log.info("Payment for " + paymentIntent.getAmount() + " succeeded.");
            }
            // Then define and call a method to handle the successful payment intent.
            // handlePaymentIntentSucceeded(paymentIntent);
            case "payment_method.attached" -> {
                PaymentMethod paymentMethod = (PaymentMethod) stripeObject;
            }
            // Then define and call a method to handle the successful attachment of a PaymentMethod.
            // handlePaymentMethodAttached(paymentMethod);
            default -> log.warn("Unhandled event type: " + event.getType());
        }
        return "";
    }
}
