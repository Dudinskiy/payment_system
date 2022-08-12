package com.example.payment_system.util.converter;

import com.example.payment_system.dto.outData.CashAccountDto;
import com.example.payment_system.entity.CashAccountEntity;
import org.springframework.stereotype.Component;

@Component
public class CashAccountDtoConverter {

    public CashAccountDto convertCashAccountToDto(CashAccountEntity accountEntity) {
        return new CashAccountDto()
                .setId(accountEntity.getId())
                .setAccountNumber(accountEntity.getAccountNumber())
                .setCurrency(accountEntity.getCurrency())
                .setAmountCurrency(accountEntity.getCurrencyAmount())
                .setUsers(accountEntity.getUser())
                .setStatus(accountEntity.getStatus());
    }
}
