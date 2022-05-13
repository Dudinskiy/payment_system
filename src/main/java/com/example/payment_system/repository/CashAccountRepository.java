package com.example.payment_system.repository;

import com.example.payment_system.entity.CashAccountEntity;
import com.example.payment_system.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CashAccountRepository extends JpaRepository<CashAccountEntity, UUID> {
    CashAccountEntity findByAccountNumber(String accountNumber);
    List<CashAccountEntity> findAllByUser(UsersEntity usersEntity);
}
