package com.eukolos.cryptoexchange.repository;

import com.eukolos.cryptoexchange.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,String> {
}
