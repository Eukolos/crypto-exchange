package com.eukolos.cryptoexchange.service;

import com.eukolos.cryptoexchange.adaptor.BinanceAdaptor;
import com.eukolos.cryptoexchange.dto.ExchangeRequest;
import com.eukolos.cryptoexchange.dto.Quote;
import com.eukolos.cryptoexchange.model.Exchange;
import com.eukolos.cryptoexchange.model.User;
import com.eukolos.cryptoexchange.repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeService {
    private final ExchangeRepository repository;
    private final BinanceAdaptor binanceAdaptor;

    public Exchange saveExchange(User user, ExchangeRequest request){
        Quote quote = binanceAdaptor.quote(request.coin());

       return repository.save(Exchange.builder()
                .currency(request.coin())
                .amount(request.amount() * Float.parseFloat(quote.price()))
                .user(user)
                .build());
    }


}
