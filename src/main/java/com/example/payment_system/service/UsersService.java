package com.example.payment_system.service;

import com.example.payment_system.dto.UsersDto;
import com.example.payment_system.dto.inputData.CreateUserInputData;

public interface UsersService {
    UsersDto createClient(CreateUserInputData createUserInputData);

    UsersDto createAdministrator(CreateUserInputData createUserInputData);

    void deleteClient();
}
