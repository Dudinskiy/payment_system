package com.example.payment_system.util.converter;

import com.example.payment_system.dto.outData.PaymentDto;
import com.example.payment_system.entity.PaymentEntity;
import org.springframework.stereotype.Component;

@Component
public class PaymentDtoConverter {

    public PaymentDto convertPaymentToDto(PaymentEntity payment) {
        return new PaymentDto().setId(payment.getId())
                .setPayAccount(payment.getPayAccount())
                .setBeneficAccount(payment.getBeneficAccount())
                .setAmountPayment(payment.getAmountPayment())
                .setOtpPassword(payment.getOtpPassword())
                .setStatus(payment.getStatus());
    }
}
