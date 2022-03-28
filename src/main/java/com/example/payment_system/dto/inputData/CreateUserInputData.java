package com.example.payment_system.dto.inputData;

import com.example.payment_system.enums.UserRole;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateUserInputData {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String userPassword;
}
