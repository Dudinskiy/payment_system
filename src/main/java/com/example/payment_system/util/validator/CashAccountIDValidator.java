package com.example.payment_system.util.validator;

import com.example.payment_system.dto.inputData.BlockCashAccountID;
import com.example.payment_system.dto.inputData.CreateCashAccountID;
import com.example.payment_system.enums.Currency;
import com.example.payment_system.exception.ValidationException;
import com.example.payment_system.util.MOD97_10Code;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CashAccountIDValidator extends IDValidator {

    private final MOD97_10Code mod97_10Code;

    public void createCashAccountIDValidate(CreateCashAccountID inputData) throws ValidationException {
        String message = "";

        if (inputData == null) {
            throw new ValidationException("Объект CreateCashAccountID является null");
        }
        if (inputData.getUserId() == null || inputData.getUserId().isEmpty()) {
            message = message + "Поле UserId не содержит значения\n";
        }
        if (inputData.getUserId() != null && !inputData.getUserId().isEmpty()) {
            if (UUIDValidate(inputData.getUserId())) {
                message = message + "Значение поля UserId не является UUID\n";
            }
        }
        if (inputData.getCurrency() == null || inputData.getCurrency().isEmpty()) {
            message = message + "Поле Currency не содержит значения\n";
        }
        if (inputData.getCurrency() != null && !inputData.getCurrency().isEmpty()) {
            if (!currencyValidate(inputData.getCurrency())) {
                message = message + "Поле Currency имеет неверное значение\n";
            }
        }

        if (!message.isEmpty()) {
            throw new ValidationException(message);
        }
    }

    public void blockCashAccountIDValidate(BlockCashAccountID inputData) throws ValidationException {
        String message = "";

        if (inputData == null) {
            throw new ValidationException("Объект BlockCashAccountID является null");
        }
        if (inputData.getAccountNumber() == null || inputData.getAccountNumber().isEmpty()) {
            message = message + "Поле AccountNumber не содержит значения\n";
        }
        if (inputData.getAccountNumber() != null && !inputData.getAccountNumber().isEmpty()) {
            if (mod97_10Code.isValidIBanNumber(inputData.getAccountNumber())) {
                message = message + "Поле AccountNumber имеет неверное значение\n";
            }
        }

        if (!message.isEmpty()) {
            throw new ValidationException(message);
        }
    }

    public boolean currencyValidate(String currency) {
        for (Currency enumCurrency : Currency.values()) {
            if (currency.toUpperCase().equals(enumCurrency.toString())) {
                return true;
            }
        }
        return false;
    }
}
