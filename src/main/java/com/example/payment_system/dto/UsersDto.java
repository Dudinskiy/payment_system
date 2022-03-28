package com.example.payment_system.dto;

import com.example.payment_system.enums.UserRole;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UsersDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private UserRole userRole;
}
