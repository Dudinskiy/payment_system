package com.example.payment_system.service;

import com.example.payment_system.dto.inputData.*;
import com.example.payment_system.dto.outData.CashAccountDto;
import com.example.payment_system.dto.outData.PutCurrencyToAccountRes;
import com.example.payment_system.entity.CashAccountEntity;
import com.example.payment_system.exception.GeneralAppException;
import com.example.payment_system.exception.ValidationException;

public interface AccountService {
    CashAccountDto createCashAccount(CreateCashAccountID inputData) throws GeneralAppException, ValidationException;
    void blockCashAccount(BlockCashAccountID inputData) throws ValidationException, GeneralAppException;
    void closeCashAccount(CloseCashAccountID inputData) throws GeneralAppException, ValidationException;
    boolean cashAccountIsEmpty(CashAccountEntity accountEntity);
    PutCurrencyToAccountRes putCurrencyToAccount(PutCurrencyToAccountID inputData)
            throws ValidationException, GeneralAppException;
    void unblockCashAccount(UnblockCashAccountID inputData) throws GeneralAppException, ValidationException;
}
