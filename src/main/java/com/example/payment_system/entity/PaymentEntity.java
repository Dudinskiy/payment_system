package com.example.payment_system.entity;

import com.example.payment_system.enums.PaymentStatus;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "payment")
public class PaymentEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pay_account_id")
    private CashAccountEntity payAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beneficiary_account_id")
    private CashAccountEntity beneficAccount;

    private BigDecimal amountPayment;

    @CreatedDate
    private LocalDateTime payDate;

    private String OtpPassword;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
