package com.example.payment_system.service;

import com.example.payment_system.dto.inputData.*;
import com.example.payment_system.dto.outData.CardDto;
import com.example.payment_system.exception.GeneralAppException;
import com.example.payment_system.exception.ValidationException;

public interface CardService {
    CardDto createCard(CreateCardID inputData) throws ValidationException;
    CardDto getCard(GetCardID inputData) throws ValidationException, GeneralAppException;
    CardDto getCardForAccount(GetCardForAccountID inputData) throws ValidationException, GeneralAppException;
    void blockCard(BlockCardID inputData) throws ValidationException, GeneralAppException;
    void closeCard(CloseCardID inputData) throws ValidationException, GeneralAppException;
}
