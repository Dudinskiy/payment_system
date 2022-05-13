package com.example.payment_system.repository;

import com.example.payment_system.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, UUID> {
    void deleteByPhoneNumber(String phoneNumber);
    UsersEntity findByPhoneNumber(String phoneNumber);
}
