package com.example.payment_system.dto.outData;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ConfirmPaymentRes {
    private String message;
}
