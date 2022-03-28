package com.example.payment_system.service;

import com.example.payment_system.entity.CashAccount;
import com.example.payment_system.entity.CreditCard;

public interface CardService {
    CreditCard createCreditCard(CashAccount account);
    void blockCreditCard(String numberCard);
    void deleteCreditCard(String numberCard);
}
