package com.example.payment_system.util;

import com.example.payment_system.dto.inputData.CreateCardID;
import com.example.payment_system.enums.CardType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CardNumberGen {

    private final LuhnCode luhnCode;

    public String getCardNumber(CreateCardID inputData) {
        String cardNumber = getPartOne(inputData.getCardType()) + getPartTwo(5) + getPartThree(9);
        int controlNumeric = luhnCode.getLuhnControlNumeral(cardNumber);

        return cardNumber + controlNumeric;
    }

    private String getPartOne(String cardType) {
        String number = null;

        if (CardType.valueOf(cardType.toUpperCase()).equals(CardType.VISA)) {
            number = "4";
        } else if (CardType.valueOf(cardType.toUpperCase()).equals(CardType.MASTERCARD)) {
            number = "5";
        }
        return number;
    }

    public String getPartTwo(int size) {
        long x = Math.round((Math.random() + 0.1) * 1000000000000000L);
        String str = Long.toString(x);

        return str.substring(0, size);
    }

    private String getPartThree(int size) {
        long x = Math.round((Math.random() + 0.1) * 1000000000000000L);
        String str = Long.toString(x);

        return str.substring(0, size);
    }

}
