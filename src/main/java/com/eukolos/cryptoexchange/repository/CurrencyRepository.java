package com.eukolos.cryptoexchange.repository;

import com.eukolos.cryptoexchange.enumaration.EnumCurrency;
import com.eukolos.cryptoexchange.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency,String> {
    Optional<Currency> findByAccount_IdAndType(String account_id, EnumCurrency type);

}
