package com.eukolos.cryptoexchange.repository;

import com.eukolos.cryptoexchange.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository<Exchange, String> {
}
