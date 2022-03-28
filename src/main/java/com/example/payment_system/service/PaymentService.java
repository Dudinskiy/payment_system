package com.example.payment_system.service;

import com.example.payment_system.entity.CreditCard;
import com.example.payment_system.entity.Payment;
import com.example.payment_system.entity.Users;

import java.util.List;

public interface PaymentService {
    void makePayment(CreditCard payerCard, CreditCard beneficiaryCard);

    List<Payment> findAllUserPayment(Users user);
}
