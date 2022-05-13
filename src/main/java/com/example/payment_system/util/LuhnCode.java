package com.example.payment_system.util;

import org.springframework.stereotype.Component;

@Component
public class LuhnCode {

    public int getLuhnControlNumeral(String value) {
        int controlNumeric;
        int end;
        int sum = getSumWithoutControlNumeric(value);
        end = sum % 10;
        if (end == 0) {
            controlNumeric = 0;
        } else {
            controlNumeric = 10 - end;
        }
        return controlNumeric;
    }

    public boolean isValidLuhn(String value) {
        int sum = Character.getNumericValue(value.charAt(value.length() - 1));
        sum = sum + getSumWithoutControlNumeric(value);

        return (sum % 10) == 0;
    }

    private int getSumWithoutControlNumeric(String value) {
        int sum = 0;
        int parity = value.length() % 2;
        for (int i = value.length() - 2; i >= 0; i--) {
            int summand = Character.getNumericValue(value.charAt(i));
            if (i % 2 == parity) {
                int product = summand * 2;
                summand = (product > 9) ? (product - 9) : product;
            }
            sum = sum + summand;
        }
        return sum;
    }
}
