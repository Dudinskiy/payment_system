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
public class Payment extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pay_account")
    private CashAccount payAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beneficiary_account")
    private CashAccount beneficAccount;

    @Column
    private BigDecimal amountPayment;

    @Column
    @CreatedDate
    private LocalDateTime payDate;

    @Column
    @Enumerated
    private PaymentStatus status;
}
