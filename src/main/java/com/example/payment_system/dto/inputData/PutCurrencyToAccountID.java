package com.example.payment_system.dto.inputData;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PutCurrencyToAccountID {
    private String cashAccountNumber;
    private String currencyAmount;
}
