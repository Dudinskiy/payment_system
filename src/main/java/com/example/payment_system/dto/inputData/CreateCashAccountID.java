package com.example.payment_system.dto.inputData;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateCashAccountID {
    private String userId;
    private String currency;
    private String balanceAccountNumber;
}
