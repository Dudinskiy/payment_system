package com.example.payment_system.util;

import com.example.payment_system.dto.UsersDto;
import com.example.payment_system.dto.inputData.CreateUserInputData;
import com.example.payment_system.entity.Users;
import com.example.payment_system.enums.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UsersDtoConverter {
    public Users convertDtoToUsersClient(CreateUserInputData userInputData) {
        return new Users().setFirstName(userInputData.getFirstName())
                .setLastName(userInputData.getLastName())
                .setPhoneNumber(userInputData.getPhoneNumber())
                .setEmail(userInputData.getEmail())
                .setUserPassword(userInputData.getUserPassword())
                .setUserRole(UserRole.CLIENT);
    }

    public Users convertDtoToUsersAdministrator(CreateUserInputData userInputData) {
        return new Users().setFirstName(userInputData.getFirstName())
                .setLastName(userInputData.getLastName())
                .setPhoneNumber(userInputData.getPhoneNumber())
                .setEmail(userInputData.getEmail())
                .setUserPassword(userInputData.getUserPassword())
                .setUserRole(UserRole.ADMINISTRATOR);
    }

    public UsersDto convertUsersToDto(Users user) {
        return new UsersDto().setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setPhoneNumber(user.getPhoneNumber())
                .setEmail(user.getEmail())
                .setUserRole(user.getUserRole());
    }
}
