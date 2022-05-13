package com.example.payment_system.service;

import com.example.payment_system.dto.outData.CardDto;
import com.example.payment_system.dto.inputData.BlockDeleteCardID;
import com.example.payment_system.dto.inputData.CreateCardID;
import com.example.payment_system.exception.GeneralAppException;
import com.example.payment_system.exception.ValidationException;

public interface CardService {
    CardDto createCard(CreateCardID inputData) throws ValidationException, GeneralAppException;
    void blockCard(BlockDeleteCardID inputData) throws ValidationException, GeneralAppException;
    void deleteCard(BlockDeleteCardID inputData) throws ValidationException, GeneralAppException;
}
