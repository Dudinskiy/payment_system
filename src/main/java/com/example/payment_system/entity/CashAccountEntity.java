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
@Table(name = "cash_account")
public class CashAccountEntity extends BaseEntity {
    @Column
    private String accountNumber;

    @Column
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column
    private BigDecimal currencyAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private UsersEntity user;

    @Column
    @Enumerated(EnumType.STRING)
    private CashAccountStatus status;
}
