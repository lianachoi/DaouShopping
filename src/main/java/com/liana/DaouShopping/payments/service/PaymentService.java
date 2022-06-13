package com.liana.DaouShopping.payments.service;

import com.liana.DaouShopping.payments.controller.repository.AccountRepository;
import com.liana.DaouShopping.payments.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final AccountRepository accountRepository;

    @Transactional
    public void createVAccount(String id){
        Account vAccount = new Account(id, 0);
        accountRepository.insert(vAccount);
    }
}
