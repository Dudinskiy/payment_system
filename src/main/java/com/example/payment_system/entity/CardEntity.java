package com.example.payment_system.entity;

import com.example.payment_system.enums.CardStatus;
import com.example.payment_system.enums.CardType;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "credit_card")
public class CardEntity extends BaseEntity {
    @Column
    private String cardNumber;

    @Column
    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @Column
    private LocalDateTime validityDate;

    @Column
    private String pinCode;

    @Column
    private String cvvCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cash_account_id")
    private CashAccountEntity cashAccount;

    @Column
    @Enumerated(EnumType.STRING)
    private CardStatus status;
}
