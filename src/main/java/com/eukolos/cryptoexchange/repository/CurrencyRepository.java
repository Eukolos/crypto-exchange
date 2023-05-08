package com.eukolos.cryptoexchange.repository;

import com.eukolos.cryptoexchange.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency,String> {
}
