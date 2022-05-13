package com.example.payment_system.unit;

import com.example.payment_system.util.MOD97_10Code;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MOD97_10CodeTest {
    private final MOD97_10Code mod97_10Code = new MOD97_10Code();

    @Test
    public void getIbanN() {
        String ibanNumber = mod97_10Code.getIBANCode("UA", "100");
        System.out.println(ibanNumber);
        Assertions.assertEquals(ibanNumber, "08");
    }

    @Test
    public void isValidIbanNumber(){
        Assertions.assertTrue(mod97_10Code.isValidIBanNumber("UA08100"));
    }
}
