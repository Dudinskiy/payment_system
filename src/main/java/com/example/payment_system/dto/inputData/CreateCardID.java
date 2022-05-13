package com.example.payment_system.dto.inputData;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateCardID {
    private String cardType;
    private String cashAccountNumber;
}
