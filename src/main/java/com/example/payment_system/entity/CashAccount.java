package com.example.payment_system.entity;

import com.example.payment_system.enums.CashAccountStatus;
import com.example.payment_system.enums.Currency;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Accessors(chain = true)
public class CashAccount extends BaseEntity {
    @Column
    private String accountNumber;

    @Column
    @Enumerated
    private Currency currency;

    @Column
    private BigDecimal amountCurrency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @Column
    @Enumerated
    private CashAccountStatus status;
}
