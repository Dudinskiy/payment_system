package com.example.payment_system.util.validator;

import com.example.payment_system.dto.inputData.BlockDeleteCardID;
import com.example.payment_system.dto.inputData.CreateCardID;
import com.example.payment_system.enums.CardType;
import com.example.payment_system.exception.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class CardIDValidator {

    public void createCardIDValidate(CreateCardID inputData) throws ValidationException {
        String message = "";

        if (inputData == null) {
            throw new ValidationException("Объект CreateCardID является null");
        }
        if (inputData.getCardType() == null || inputData.getCardType().isEmpty()) {
            message = message + "Поле CardType не содержит значения\n";
        }
        if (inputData.getCardType() != null && !inputData.getCardType().isEmpty()) {
            if (!cardTypeValidate(inputData.getCardType())) {
                message = message + "Поле CardType имеет неверное значение\n";
            }
        }

        if (!message.isEmpty()) {
            throw new ValidationException(message);
        }
    }

    public void blockDeleteCardIDValidate(BlockDeleteCardID inputData) throws ValidationException {
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

    public boolean cardTypeValidate(String cardType) {
        for (CardType enumCardType : CardType.values()) {
            if (cardType.toUpperCase().equals(enumCardType.toString())) {
                return true;
            }
        }
        return false;
    }
}
