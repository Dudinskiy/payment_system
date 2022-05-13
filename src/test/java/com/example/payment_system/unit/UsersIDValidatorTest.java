package com.example.payment_system.unit;

import com.example.payment_system.util.validator.UsersIDValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class UsersIDValidatorTest {

    private static ArrayList<String> phoneSet;
    private static ArrayList<String> emailSet;
    private static ArrayList<String> passwordSet;
    private UsersIDValidator validator;

    @BeforeAll
    static void prepareTestSet() {
        phoneSet = new ArrayList<>();
        phoneSet.add("(096) 128-0-871");
        phoneSet.add("(063) 057-69-50");
        phoneSet.add("(097) 790 75 95");
        phoneSet.add("(092) 790 75 98");
        phoneSet.add("98720-0-0104");
        phoneSet.add("5700-145748");
        phoneSet.add("(096) 1280871");
        phoneSet.add("(065) 0576950");
        phoneSet.add("096) 128-08-71");
        phoneSet.add("(096) 128 08 71");
        phoneSet.add("(063) 057-69-5");
        phoneSet.add("(092) 057-69-50");
        phoneSet.add("097) 8151558");
        phoneSet.add("097) 7907595");
        phoneSet.add("-198720-0-010");
        phoneSet.add("-1457486892");
        phoneSet.add("096) 1280871");
        phoneSet.add("063) 0576950");
        phoneSet.add("+38 (094) 400 99 44");
        phoneSet.add("+7 (926) 597-76-71");
        phoneSet.add("+ 38096273-13-27");
        phoneSet.add("+380634009944");
        phoneSet.add("+38 (094) 400 99 44");
        phoneSet.add("+38 066 019 30 90");
        phoneSet.add("8 (999) 99-999-99");
        phoneSet.add("001-541-754-3010");
        phoneSet.add("+ 1-541-754-3010");
        phoneSet.add("19-49-89-636-48018");
        phoneSet.add("+3050 35599853");
        phoneSet.add("+305035599853");

        emailSet = new ArrayList<>();
        emailSet.add("123@gmail.com");
        emailSet.add("123gmail.com");
        emailSet.add("123@.com");
        emailSet.add("123@gmailcom");
        emailSet.add("123@gmail.");
        emailSet.add("@gmail.com");

        passwordSet = new ArrayList<>();
        passwordSet.add("Aa1234");
        passwordSet.add("Aa123");
        passwordSet.add("Aa 234");
        passwordSet.add("Aa234 ");
        passwordSet.add("Aa1234");
        passwordSet.add(" Aa123");
        passwordSet.add("aa1234");
        passwordSet.add("AA1234");
        passwordSet.add("123456");
        passwordSet.add("adfrgh");
        passwordSet.add("ADFRGH");
    }

    @BeforeEach
    public void init() {
        validator = new UsersIDValidator();
    }

    @Test
    public void phoneNumberTest() {
        boolean result;
        for (String number : phoneSet) {
            result = validator.phoneValidate(number);
            if (result) {
                System.out.printf("%s соответствует\n", number);
            } else {
                System.out.printf("%s не соответствует\n", number);
            }
        }
        System.out.println();
    }

    @Test
    public void emailTest(){
        boolean result;
        for (String email : emailSet) {
            result = validator.emailValidate(email);
            if (result) {
                System.out.printf("%s соответствует\n", email);
            } else {
                System.out.printf("%s не соответствует\n", email);
            }
        }
        System.out.println();
    }

    @Test
    public void passwordTest(){
        boolean result;
        for (String password : passwordSet) {
            result = validator.passwordValidate(password);
            if (result) {
                System.out.printf("%s соответствует\n", password);
            } else {
                System.out.printf("%s не соответствует\n", password);
            }
        }
        System.out.println();
    }
}
