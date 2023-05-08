package com.eukolos.cryptoexchange.repository;

import com.eukolos.cryptoexchange.model.CryptoAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoAccountRepository extends JpaRepository<CryptoAccount,String> {
}
