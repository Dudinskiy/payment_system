package com.example.payment_system.util.validator;

import com.example.payment_system.dto.inputData.*;
import com.example.payment_system.exception.ValidationException;
import com.example.payment_system.util.AccountNumberControlCode;
import com.example.payment_system.util.MOD97_10Code;
import org.springframework.stereotype.Component;

@Component
public class CardIDValidator extends IDValidator {
    public CardIDValidator(MOD97_10Code mod97_10Code, AccountNumberControlCode numberControlCode) {
        super(mod97_10Code, numberControlCode);
    }

    public void createCardIDValidate(CreateCardID inputData) throws ValidationException {
        String message = "";

        if (inputData == null) {
            throw new ValidationException("Объект CreateCardID является null");
        }
        message = checkCardTypeField(inputData.getCardType(), message);

        if (!message.isEmpty()) {
            throw new ValidationException(message);
        }
    }

    public void getCardIDValidate(GetCardID inputData) throws ValidationException {
        String message = "";

        if (inputData == null) {
            throw new ValidationException("Объект GetCardID является null");
        }
        message = checkCardTypeField(inputData.getCardType(), message);
        message = checkCurrencyField(inputData.getCurrency(), message);
        message = checkPhoneNumberField(inputData.getPhoneNumber(), message);

        if (!message.isEmpty()) {
            throw new ValidationException(message);
        }
    }

    public void getCardForAccountIDValidate(GetCardForAccountID inputData) throws ValidationException {
        String message = "";

        if (inputData == null) {
            throw new ValidationException("Объект getCardForAccountID является null");
        }
        message = checkCardTypeField(inputData.getCardType(), message);
        message = checkCashAccountNumberField(inputData.getCashAccountNumber(),
                "CashAccountNumber", message);

        if (!message.isEmpty()) {
            throw new ValidationException(message);
        }

    }

    public void blockCardIDValidate(BlockCardID inputData) throws ValidationException {
        String message = "";

        if (inputData == null) {
            throw new ValidationException("Объект BlockDeleteCardID является null");
        }
        //Добавить проверку на длину номера
        //Добавить проверку на наличие других символов кроме цифр. Пробелы допускаются. Нужно подрпавить метод!!!
        if (inputData.getCardNumber() == null || inputData.getCardNumber().isEmpty()) {
            message = message + "Поле CardNumber не содержит значения\n";
        }

        if (!message.isEmpty()) {
            throw new ValidationException(message);
        }
    }

    public void closeCardIDValidate(CloseCardID inputData) throws ValidationException {
        String message = "";

        if (inputData == null) {
            throw new ValidationException("Объект CloseCardID является null");
        }
        //Добавить проверку на длину номера
        //Добавить проверку на наличие других символов кроме цифр. Пробелы допускаются. Нужно подрпавить метод!!!
        if (inputData.getCardNumber() == null || inputData.getCardNumber().isEmpty()) {
            message = message + "Поле CardNumber не содержит значения\n";
        }

        if (!message.isEmpty()) {
            throw new ValidationException(message);
        }
    }
}
