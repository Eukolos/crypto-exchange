package com.eukolos.cryptoexchange.repository;

import com.eukolos.cryptoexchange.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
