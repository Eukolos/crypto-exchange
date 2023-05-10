package com.eukolos.cryptoexchange.dto;

import com.eukolos.cryptoexchange.enumaration.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserDto implements Serializable {
    private final String email;
    private final String password;
    private final Role role;
    private final List<CryptoAccountDto> cryptoAccountList;
}