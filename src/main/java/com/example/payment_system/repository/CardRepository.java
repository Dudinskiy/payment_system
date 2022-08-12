package com.example.payment_system.repository;

import com.example.payment_system.entity.CardEntity;
import com.example.payment_system.enums.CardStatus;
import com.example.payment_system.enums.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, UUID> {
    CardEntity findByCardNumber(String cardNumber);
    CardEntity findByCardTypeAndStatus(CardType type, CardStatus status);
    List<CardEntity> findAllByCardTypeAndStatus(CardType type, CardStatus status);
}
