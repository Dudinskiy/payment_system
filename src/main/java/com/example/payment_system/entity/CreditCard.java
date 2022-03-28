package com.example.payment_system.entity;

import com.example.payment_system.enums.CreditCardStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain = true)
public class CreditCard extends BaseEntity {
    @Column
    private String cardNumber;

    @Column
    private String cardType;

    @Column
    private String validityDate;

    @Column
    private String pinCode;

    @Column
    private String cvvCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cash_account_id")
    private CashAccount cashAccount;

    @Column
    @Enumerated
    private CreditCardStatus status;
}
