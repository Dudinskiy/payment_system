package com.example.payment_system.dto.inputData;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DeleteUserID {
    private String phoneNumber;
}
