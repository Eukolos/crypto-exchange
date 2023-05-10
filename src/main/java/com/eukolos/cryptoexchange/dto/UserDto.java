package com.eukolos.cryptoexchange.dto;

import com.eukolos.cryptoexchange.enumaration.Role;
import com.eukolos.cryptoexchange.model.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto implements Serializable {
    private final String email;
    private final String password;
    private final Role role;
    private final List<CryptoAccountDto> cryptoAccountDtoList;
    private final List<PaymentDto> paymentDtoList;

    public UserDto(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();

        List<CryptoAccountDto> cryptoAccountDtoList = new ArrayList<>();
        for (CryptoAccount cryptoAccount : user.getCryptoAccountList()) {
            cryptoAccountDtoList.add(new CryptoAccountDto(cryptoAccount));
        }
        this.cryptoAccountDtoList = cryptoAccountDtoList;

        List<PaymentDto> paymentDtoList = new ArrayList<>();
        for (Payment payment : user.getPaymentList() ){
            paymentDtoList.add(new PaymentDto(payment));
        }
        this.paymentDtoList = paymentDtoList;
    }
}