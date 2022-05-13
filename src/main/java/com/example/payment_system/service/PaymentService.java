package com.example.payment_system.service;

import com.example.payment_system.dto.outData.ConfirmPaymentRes;
import com.example.payment_system.dto.outData.PaymentDto;
import com.example.payment_system.dto.inputData.ConfirmPaymentID;
import com.example.payment_system.dto.inputData.CreatePaymentID;
import com.example.payment_system.entity.PaymentEntity;
import com.example.payment_system.entity.UsersEntity;
import com.example.payment_system.exception.GeneralAppException;
import com.example.payment_system.exception.ValidationException;

import java.util.List;

public interface PaymentService {
    PaymentDto createPayment(CreatePaymentID inputData) throws ValidationException, GeneralAppException;
    ConfirmPaymentRes confirmPayment(ConfirmPaymentID inputData) throws ValidationException, GeneralAppException;

    List<PaymentEntity> findAllUserPayment(UsersEntity user);
}
