package com.example.payment_system.dto.inputData;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Класс содержит исходные данные для создания
 * платежной карты
 */

@Data
@Accessors(chain = true)
public class GetCardID {
    private String cardType;
    private String currency;
    private String phoneNumber;
}
