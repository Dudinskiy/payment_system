package com.example.payment_system.service;

import com.example.payment_system.entity.CashAccount;
import com.example.payment_system.entity.Users;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{

    @Override
    public CashAccount createCashAccount(Users user) {
        return null;
    }

    @Override
    public void blockCashAccount(Users user) {

    }

    @Override
    public void deleteCashAccount(Users user) {

    }
}
