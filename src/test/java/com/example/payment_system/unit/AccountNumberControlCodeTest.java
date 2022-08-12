package com.example.payment_system.unit;

import com.example.payment_system.util.AccountNumberControlCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountNumberControlCodeTest {
    private final AccountNumberControlCode controlCode = new AccountNumberControlCode();
    private static final String IBAN = "UA142803802620184065567280741";
    private static final String PRE_BBAN = "2803802620084065567280741";
    private static final String PRE_BBAN_1 = "3000001211072801123456789";

    @Test
    public void isValidAccountNumberNumeric() {
        Assertions.assertTrue(controlCode.isValidAccountNumberNumeric(IBAN));
    }

    @Test
    public void getControlNumeric() {
        int number = controlCode.getControlNumeric(PRE_BBAN_1);
        Assertions.assertEquals(number, 6);

        int number1 = controlCode.getControlNumeric(PRE_BBAN);
        Assertions.assertEquals(number1, 1);
    }
}
