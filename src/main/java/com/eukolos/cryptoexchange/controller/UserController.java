package com.eukolos.cryptoexchange.controller;

import com.eukolos.cryptoexchange.dto.LoginRequest;
import com.eukolos.cryptoexchange.dto.UserDto;
import com.eukolos.cryptoexchange.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public UserDto registerUser(@RequestBody LoginRequest request){
        return service.registerUser(request);
    }
    @GetMapping
    public UserDto findUserByEmail(@RequestParam("email") String email ) {
        return service.findUserByEmail(email);
    }

}
