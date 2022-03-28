package com.example.payment_system.service;

import com.example.payment_system.entity.CashAccount;
import com.example.payment_system.entity.Users;

public interface AccountService {
    CashAccount createCashAccount(Users user);
    void blockCashAccount(Users user);
    void deleteCashAccount(Users user);
}
