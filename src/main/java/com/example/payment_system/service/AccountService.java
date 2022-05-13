package com.example.payment_system.service;

import com.example.payment_system.dto.outData.CashAccountDto;
import com.example.payment_system.dto.inputData.BlockCashAccountID;
import com.example.payment_system.dto.inputData.CreateCashAccountID;
import com.example.payment_system.entity.CashAccountEntity;
import com.example.payment_system.exception.GeneralAppException;
import com.example.payment_system.exception.ValidationException;

public interface AccountService {
    CashAccountDto createCashAccount(CreateCashAccountID inputData) throws GeneralAppException, ValidationException;
    void blockCashAccount(BlockCashAccountID inputData) throws ValidationException, GeneralAppException;
    void deleteCashAccount(BlockCashAccountID user);
    boolean cashAccountIsEmpty(CashAccountEntity accountEntity);
}
