package com.example.payment_system.dto.inputData;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
public class ConfirmPaymentID {
    private String paymentId;
    private String password;
}
