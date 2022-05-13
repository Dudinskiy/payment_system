package com.example.payment_system.dto.outData;

import com.example.payment_system.entity.CashAccountEntity;
import com.example.payment_system.enums.CardStatus;
import com.example.payment_system.enums.CardType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Accessors(chain = true)
public class CardDto {
    private UUID id;
    private String cardNumber;
    private CardType cardType;
    private LocalDateTime validityDate;
    private String pinCode;
    private String cvvCode;
    private CashAccountEntity cashAccount;
    private CardStatus status;
}
