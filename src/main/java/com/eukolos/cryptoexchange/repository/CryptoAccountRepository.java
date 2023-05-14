package com.eukolos.cryptoexchange.repository;

import com.eukolos.cryptoexchange.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoAccountRepository extends JpaRepository<Account,String> {
}
