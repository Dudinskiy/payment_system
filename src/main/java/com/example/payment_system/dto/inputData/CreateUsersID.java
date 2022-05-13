package com.example.payment_system.dto.inputData;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateUsersID {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String userPassword;
}
