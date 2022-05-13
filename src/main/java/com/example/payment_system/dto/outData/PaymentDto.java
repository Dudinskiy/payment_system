package com.example.payment_system.dto.outData;

import com.example.payment_system.entity.CashAccountEntity;
import com.example.payment_system.enums.PaymentStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Accessors(chain = true)
public class PaymentDto {
    private UUID id;
    private CashAccountEntity payAccount;
    private CashAccountEntity beneficAccount;
    private BigDecimal amountPayment;
    private LocalDateTime payDate;
    private String OtpPassword;
    private PaymentStatus status;
}
