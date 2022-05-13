package com.example.payment_system.util;

import com.example.payment_system.dto.inputData.CreateCashAccountID;
import com.example.payment_system.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Data
public class CashAccountNumberGen {
    private static final String COUNTRY_CODE = "UA";
    private static final String BANK_CODE = "280380";
    private static final String ACCOUNT_CLASS = "2620";
    private static final String UAH_CODE = "980";
    private static final String USD_CODE = "840";
    private static final String EUR_CODE = "978";
    private static final String RUB_CODE = "643";
    private final AccountNumberControlCode controlCode;
    private final MOD97_10Code mod97_10Code;

    public String getCashAccountNumber(CreateCashAccountID inputData) {
        String basicBankAccountNumber = basicBankAccountNumber(inputData);
        String ibanCode = mod97_10Code.getIBANCode(COUNTRY_CODE, basicBankAccountNumber);

        return COUNTRY_CODE + ibanCode + basicBankAccountNumber;
    }

    private String basicBankAccountNumber(CreateCashAccountID inputData) {
        String currencyCode = getCurrencyCode(inputData);
        String partThree = getPartThree();
        String number = BANK_CODE + ACCOUNT_CLASS + "0" + currencyCode + partThree;

        return BANK_CODE + ACCOUNT_CLASS + controlCode.getControlNumeric(number) + currencyCode + partThree;
    }

    private String getCurrencyCode(CreateCashAccountID inputData) {
        if (inputData.getCurrency().toUpperCase().equals(Currency.UAH.toString())) {
            return UAH_CODE;
        } else if (inputData.getCurrency().toUpperCase().equals(Currency.USD.toString())) {
            return USD_CODE;
        } else if (inputData.getCurrency().toUpperCase().equals(Currency.EUR.toString())) {
            return EUR_CODE;
        } else {
            return RUB_CODE;
        }
    }

    private String getPartThree() {
        long x = Math.round((Math.random() + 0.1) * 10000000000000L);
        String str = Long.toString(x);

        return str.substring(0, 11);
    }
}

