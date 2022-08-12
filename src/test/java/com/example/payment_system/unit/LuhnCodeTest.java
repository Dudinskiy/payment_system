package com.example.payment_system.unit;

import com.example.payment_system.util.LuhnCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LuhnCodeTest {
    private static final String CARD_NUM_1 = "4149499380535078";
    private static final String CARD_NUM_2 = "6768721000060749";
    private static final String CARD_NUM_3 = "4765646571935739";
    private final LuhnCode luhnCode = new LuhnCode();

    @Test
    public void checkLuhnCode() {
        Assertions.assertTrue(luhnCode.isValidLuhn(CARD_NUM_1));
        Assertions.assertTrue(luhnCode.isValidLuhn(CARD_NUM_2));
        Assertions.assertTrue(luhnCode.isValidLuhn(CARD_NUM_3));
    }
}
