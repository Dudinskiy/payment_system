package com.example.payment_system.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class MOD97_10Code {
    private static final Map<String, String> letterNumericMapping;

    static {
        letterNumericMapping = new HashMap<>();
        letterNumericMapping.put("A", "10");
        letterNumericMapping.put("B", "11");
        letterNumericMapping.put("C", "12");
        letterNumericMapping.put("D", "13");
        letterNumericMapping.put("E", "14");
        letterNumericMapping.put("F", "15");
        letterNumericMapping.put("G", "16");
        letterNumericMapping.put("H", "17");
        letterNumericMapping.put("I", "18");
        letterNumericMapping.put("J", "19");
        letterNumericMapping.put("K", "20");
        letterNumericMapping.put("L", "21");
        letterNumericMapping.put("M", "22");
        letterNumericMapping.put("N", "23");
        letterNumericMapping.put("O", "24");
        letterNumericMapping.put("P", "25");
        letterNumericMapping.put("Q", "26");
        letterNumericMapping.put("R", "27");
        letterNumericMapping.put("S", "28");
        letterNumericMapping.put("T", "29");
        letterNumericMapping.put("U", "30");
        letterNumericMapping.put("V", "31");
        letterNumericMapping.put("W", "32");
        letterNumericMapping.put("X", "33");
        letterNumericMapping.put("Y", "34");
        letterNumericMapping.put("Z", "35");
    }

    public boolean isValidIBanNumber(String number) {
        String countryCode = number.substring(0, 2);
        String ibanCode = number.substring(2, 4);
        String basicBankAccountNumber = number.substring(4);

        String n = basicBankAccountNumber + getNumberFromCountryCode(countryCode) + ibanCode;
        BigDecimal residue = getResidue(n);
        int compareRes = residue.compareTo(new BigDecimal("1"));

        return compareRes == 0;
    }

    public String getIBANCode(String countryCode, String basicBankAccountNumber) {
        String number = basicBankAccountNumber + getNumberFromCountryCode(countryCode) + "00";
        BigDecimal subtractRes1 = getResidue(number);
        BigDecimal subtractRes2 = new BigDecimal("98").subtract(subtractRes1);

        int compares = subtractRes2.compareTo(new BigDecimal("10"));
        if (compares < 0) {
            return "0" + subtractRes2;
        } else {
            return subtractRes2.toString();
        }
    }

    private String getNumberFromCountryCode(String countryCode) {
        String fistCharacter = countryCode.substring(0, 1).toUpperCase();
        String secondCharacter = countryCode.substring(1).toUpperCase();

        return letterNumericMapping.get(fistCharacter) + letterNumericMapping.get(secondCharacter);
    }

    private BigDecimal getResidue(String number) {
        BigDecimal n = new BigDecimal(number);
        BigDecimal divideRes = n.divide(new BigDecimal("97"), BigDecimal.ROUND_FLOOR);
        BigDecimal multiplyRes = divideRes.multiply(new BigDecimal("97"));

        return n.subtract(multiplyRes);
    }
}
