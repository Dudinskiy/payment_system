package com.example.payment_system.util;

import org.springframework.stereotype.Component;

@Component
public class AccountNumberControlCode {

    public boolean isValidAccountNumberNumeric(String number) {
        String basicBankAccountNumber = number.substring(4);
        int controlNumeric = Character.getNumericValue(number.charAt(14));

        StringBuilder preBBAN = new StringBuilder(basicBankAccountNumber);
        preBBAN.setCharAt(10, '0');

        return controlNumeric == getControlNumeric(preBBAN.toString());
    }

    public int getControlNumeric(String basicBankAccountNumber) {
        String bankCode = basicBankAccountNumber.substring(0, 6);
        String accountNumber = basicBankAccountNumber.substring(6);

        int sumForBankCode = getSumForBankCode(bankCode);
        int sumForAccountNumber = getSumForAccountNumber(accountNumber);

        int sum = sumForBankCode + sumForAccountNumber + accountNumber.length();

        String sumStr = String.valueOf(sum);
        int sumLastNumeric = Character.getNumericValue(sumStr.charAt(sumStr.length() - 1));

        return (sumLastNumeric * 7) % 10;
    }

    private int getSumForBankCode(String bankCode) {
        return getSum(bankCode, 1, 3, 7);
    }

    private int getSumForAccountNumber(String accountNumber) {
        return getSum(accountNumber, 3, 7, 1);
    }

    private int getSum(String value, int coef1, int coef2, int coef3) {
        int m = 0;
        int n = 1;
        int sum = 0;
        int res;
        for (int i = 0; i < value.length(); i++) {
            int numeric = Character.getNumericValue(value.charAt(i));
            if (i == m) {
                res = numeric * coef1;
                if (res > 9) {
                    sum = sum + (res % 10);
                } else {
                    sum = sum + res;
                }
                m = m + 3;
            } else if (i == n) {
                res = numeric * coef2;
                if (res > 9) {
                    sum = sum + (res % 10);
                } else {
                    sum = sum + res;
                }
                n = n + 3;
            } else {
                res = numeric * coef3;
                if (res > 9) {
                    sum = sum + (res % 10);
                } else {
                    sum = sum + res;
                }
            }
        }
        return sum;
    }
}
