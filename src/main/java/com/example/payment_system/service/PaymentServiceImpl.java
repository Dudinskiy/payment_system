package com.example.payment_system.service;

import com.example.payment_system.entity.CreditCard;
import com.example.payment_system.entity.Payment;
import com.example.payment_system.entity.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public void makePayment(CreditCard payerCard, CreditCard beneficiaryCard) {

    }

    @Override
    public List<Payment> findAllUserPayment(Users user) {
        return null;
    }
}
