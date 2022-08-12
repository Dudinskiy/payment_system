package com.example.payment_system.util.validator;

import com.example.payment_system.enums.CardType;
import com.example.payment_system.enums.Currency;
import com.example.payment_system.util.AccountNumberControlCode;
import com.example.payment_system.util.MOD97_10Code;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
@Data
public class IDValidator {
    private final MOD97_10Code mod97_10Code;
    private final AccountNumberControlCode numberControlCode;


    public String checkPhoneNumberField(String field, String message) {
        if (field == null || field.isEmpty()) {
            message = message + "Поле PhoneNumber не содержит значения\n";
        }
        if (field != null && !field.isEmpty()) {
            if (!phoneValidate(field)) {
                message = message + "Поле PhoneNumber имеет неверное значение\n";
            }
        }
        return message;
    }

    public boolean phoneValidate(String phone) {
        String regForPhone = "^((\\+[\\- ]?38)[\\- ]?)?(\\(?(039)?(067)?(068)?(096)?(097)?" +
                "(098)?(050)?(066)?(095)?(099)?(063)?(073)?(093)?" +
                "(091)?(092)?(089)?(094)?\\)?[\\- ]?)?[\\d\\- ]{7,9}$";

        Pattern pattern = Pattern.compile(regForPhone);
        Matcher matcher = pattern.matcher(phone);

        return matcher.matches();
    }

    public String checkCurrencyField(String field, String message) {
        if (field == null || field.isEmpty()) {
            message = message + "Поле Currency не содержит значения\n";
        }
        if (field != null && !field.isEmpty()) {
            if (!currencyValidate(field)) {
                message = message + "Поле Currency имеет неверное значение\n";
            }
        }
        return message;
    }

    public boolean currencyValidate(String currency) {
        for (Currency enumCurrency : Currency.values()) {
            if (currency.toUpperCase().equals(enumCurrency.toString())) {
                return true;
            }
        }
        return false;
    }

    public String checkCardTypeField(String field, String message) {
        if (field == null || field.isEmpty()) {
            message = message + "Поле CardType не содержит значения\n";
        }
        if (field != null && !field.isEmpty()) {
            if (!cardTypeValidate(field)) {
                message = message + "Поле CardType имеет неверное значение\n";
            }
        }
        return message;
    }

    public boolean cardTypeValidate(String cardType) {
        for (CardType enumCardType : CardType.values()) {
            if (cardType.toUpperCase().equals(enumCardType.toString())) {
                return true;
            }
        }
        return false;
    }

    public String checkCashAccountNumberField(String field, String fieldName, String message) {
        if (field == null || field.isEmpty()) {
            message = message + "Поле " + fieldName + " не содержит значения\n";
        }
        if (field != null && !field.isEmpty()) {
            if (!numberControlCode.isValidAccountNumberNumeric(field)) {
                message = message + "Поле " + fieldName + " имеет неверное значение (BBAN)\n";
            }
            if (!mod97_10Code.isValidIBanNumber(field)) {
                message = message + "Поле " + fieldName + " имеет неверное значение (IBAN)\n";
            }
        }
        return message;
    }

    public String checkCurrencyAmountField(String field, String message) {
        if (field == null || field.isEmpty()) {
            message = message + "Поле CurrencyAmount не содержит значения\n";
        }
        if (field != null && !field.isEmpty()) {
            if (!isNumberAndHasRequiredAccuracy(field)) {
                message = message + "Поле CurrencyAmount не является числом или имеет недопустимое значение";

            } else if (lessThanMinAmount(field)) {
                message = message + "Сумма платежа не может быть меньше чем 0.01\n";
            }
        }
        return message;
    }

    private boolean lessThanMinAmount(String payAmount) {
        int compareRes = new BigDecimal(payAmount).compareTo(new BigDecimal("0.01"));

        return compareRes < 0;
    }

    private boolean isNumberAndHasRequiredAccuracy(String number) {
        String regForNumber = "^([1-9]\\d*\\.\\d{1,2}|0\\.\\d{1,2}|0|[1-9]\\d+|[1-9])$";

        Pattern pattern = Pattern.compile(regForNumber);
        Matcher matcher = pattern.matcher(number);

        return matcher.matches();
    }
}
