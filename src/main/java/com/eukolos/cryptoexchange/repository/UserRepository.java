package com.eukolos.cryptoexchange.repository;

import com.eukolos.cryptoexchange.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByEmail(String email);
}