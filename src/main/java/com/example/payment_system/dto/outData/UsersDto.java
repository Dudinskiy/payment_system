package com.example.payment_system.dto.outData;

import com.example.payment_system.enums.UserRole;
import com.example.payment_system.enums.UserStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
public class UsersDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private UserRole userRole;
    private UserStatus userStatus;
}
