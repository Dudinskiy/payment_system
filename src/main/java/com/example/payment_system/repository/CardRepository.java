package com.example.payment_system.repository;

import com.example.payment_system.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, UUID> {
    CardEntity findByCardNumber(String cardNumber);
}
