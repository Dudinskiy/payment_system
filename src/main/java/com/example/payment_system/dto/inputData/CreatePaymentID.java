package com.example.payment_system.dto.inputData;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class CreatePaymentID {
    private String payAccountNumber;
    private String benefAccountNumber;
    private String payCardNumber;
    private String benefCardNumber;
    private String amountPayment;
}
