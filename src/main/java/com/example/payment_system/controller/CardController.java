package com.example.payment_system.controller;

import com.example.payment_system.dto.inputData.CreateCardID;
import com.example.payment_system.dto.inputData.GetCardForAccountID;
import com.example.payment_system.dto.inputData.GetCardID;
import com.example.payment_system.dto.outData.CardDto;
import com.example.payment_system.exception.GeneralAppException;
import com.example.payment_system.exception.ValidationException;
import com.example.payment_system.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/payment-system/card")
public class CardController {
    private final CardService cardService;

    @PostMapping("/create")
    public CardDto createCard(@RequestBody CreateCardID inputData)
            throws ValidationException {
        return cardService.createCard(inputData);
    }

    @PostMapping("/get-for-account")
    public CardDto getCardForAccount(@RequestBody GetCardForAccountID inputData)
            throws ValidationException, GeneralAppException {
        return cardService.getCardForAccount(inputData);
    }

    @PostMapping("/get")
    public CardDto getCard(@RequestBody GetCardID inputData)
            throws ValidationException, GeneralAppException {
        return cardService.getCard(inputData);
    }

}
