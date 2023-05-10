package com.eukolos.cryptoexchange.service;

import com.eukolos.cryptoexchange.dto.LoginRequest;
import com.eukolos.cryptoexchange.exception.EmailNotFoundException;
import com.eukolos.cryptoexchange.model.User;
import com.eukolos.cryptoexchange.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User registerUser(LoginRequest request){
        return repository.save(
                User.builder().email(request.getEmail()).password(request.getPassword()).build()
                );
    }

    public void getUserByEmail(String email){
        repository.findUserByEmail(email).orElseThrow(() -> new EmailNotFoundException("User not found with email: {} " + email));
    }

}
