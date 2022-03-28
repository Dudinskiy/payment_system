package com.example.payment_system.service;

import com.example.payment_system.entity.CashAccount;
import com.example.payment_system.entity.CreditCard;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {
    @Override
    public CreditCard createCreditCard(CashAccount account) {
        return null;
    }

    @Override
    public void blockCreditCard(String numberCard) {

    }

    @Override
    public void deleteCreditCard(String numberCard) {

    }
}
