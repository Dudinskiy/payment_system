package com.example.payment_system.util.converter;

import com.example.payment_system.dto.outData.CardDto;
import com.example.payment_system.entity.CardEntity;
import org.springframework.stereotype.Component;

@Component
public class CardConverter {

    public CardDto convertCardToDto(CardEntity cardEntity) {
        return new CardDto().setId(cardEntity.getId())
                .setCardNumber(cardEntity.getCardNumber())
                .setCardType(cardEntity.getCardType())
                .setValidityDate(cardEntity.getValidityDate())
                .setPinCode(cardEntity.getPinCode())
                .setCvvCode(cardEntity.getCvvCode())
                .setCashAccount(cardEntity.getCashAccount())
                .setStatus(cardEntity.getStatus());
    }
}
