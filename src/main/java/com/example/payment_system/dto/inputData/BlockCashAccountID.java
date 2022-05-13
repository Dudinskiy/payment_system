package com.example.payment_system.dto.inputData;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BlockCashAccountID {
    private String accountNumber;
}
