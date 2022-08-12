package com.example.payment_system.util.validator;

import com.example.payment_system.dto.inputData.*;
import com.example.payment_system.exception.ValidationException;
import com.example.payment_system.util.AccountNumberControlCode;
import com.example.payment_system.util.MOD97_10Code;
import org.springframework.stereotype.Component;

@Component
public class CashAccountIDValidator extends IDValidator {
    public CashAccountIDValidator(MOD97_10Code mod97_10Code,
                                  AccountNumberControlCode numberControlCode) {
        super(mod97_10Code, numberControlCode);
    }

    public void createCashAccountIDValidate(CreateCashAccountID inputData) throws ValidationException {
        String message = "";

        if (inputData == null) {
            throw new ValidationException("Объект CreateCashAccountID является null");
        }
        message = checkPhoneNumberField(inputData.getPhoneNumber(), message);
        message = checkCurrencyField(inputData.getCurrency(), message);

        if (!message.isEmpty()) {
            throw new ValidationException(message);
        }
    }

    public void blockCashAccountIDValidate(BlockCashAccountID inputData) throws ValidationException {
        String message = "";

        if (inputData == null) {
            throw new ValidationException("Объект BlockCashAccountID является null");
        }
        message = checkCashAccountNumberField(inputData.getCashAccountNumber(),
                "CashAccountNumber", message);

        if (!message.isEmpty()) {
            throw new ValidationException(message);
        }
    }

    public void UnblockCashAccountIDValidate(UnblockCashAccountID inputData) throws ValidationException {
        String message = "";

        if (inputData == null) {
            throw new ValidationException("Объект UnblockCashAccountID является null");
        }
        message = checkCashAccountNumberField(inputData.getCashAccountNumber(),
                "CashAccountNumber", message);

        if (!message.isEmpty()) {
            throw new ValidationException(message);
        }
    }

    public void closeCashAccountIDValidate(CloseCashAccountID inputData) throws ValidationException {
        String message = "";

        if (inputData == null) {
            throw new ValidationException("Объект CloseCashAccountID является null");
        }
        message = checkCashAccountNumberField(inputData.getCashAccountNumber(),
                "CashAccountNumber", message);

        if (!message.isEmpty()) {
            throw new ValidationException(message);
        }
    }

    public void PutCashToAccountIDValidate(PutCurrencyToAccountID inputData) throws ValidationException {
        String message = "";

        if (inputData == null) {
            throw new ValidationException("Объект PutCashToAccountID является null");
        }
        message = checkCashAccountNumberField(inputData.getCashAccountNumber(),
                "CashAccountNumber", message);
        message = checkCurrencyAmountField(inputData.getCurrencyAmount(), message);

        if (!message.isEmpty()) {
            throw new ValidationException(message);
        }
    }
}
