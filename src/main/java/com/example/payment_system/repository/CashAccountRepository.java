package com.example.payment_system.repository;

import com.example.payment_system.entity.CashAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashAccountRepository extends JpaRepository<CashAccount, Integer> {
}
