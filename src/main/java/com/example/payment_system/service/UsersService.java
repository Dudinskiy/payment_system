package com.example.payment_system.service;

import com.example.payment_system.dto.outData.UsersDto;
import com.example.payment_system.dto.inputData.CreateUsersID;
import com.example.payment_system.dto.inputData.DeleteUserID;
import com.example.payment_system.exception.GeneralAppException;
import com.example.payment_system.exception.ValidationException;

public interface UsersService {
    UsersDto createClient(CreateUsersID createUserInputData) throws ValidationException;

    UsersDto createAdministrator(CreateUsersID createUserInputData);

    void deleteClientByPhone(DeleteUserID deleteUserInputData) throws GeneralAppException;

}
