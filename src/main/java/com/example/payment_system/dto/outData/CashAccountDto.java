package com.example.payment_system.dto.outData;

import com.example.payment_system.entity.UsersEntity;
import com.example.payment_system.enums.CashAccountStatus;
import com.example.payment_system.enums.Currency;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Accessors(chain = true)
public class CashAccountDto {
    private UUID id;
    private String accountNumber;
    private Currency currency;
    private BigDecimal amountCurrency;
    private UsersEntity users;
    private CashAccountStatus status;
}
