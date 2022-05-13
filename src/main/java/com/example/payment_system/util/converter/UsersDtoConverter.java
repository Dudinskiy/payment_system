package com.example.payment_system.util.converter;

import com.example.payment_system.dto.outData.UsersDto;
import com.example.payment_system.dto.inputData.CreateUsersID;
import com.example.payment_system.entity.UsersEntity;
import com.example.payment_system.enums.UserRole;
import com.example.payment_system.enums.UserStatus;
import org.springframework.stereotype.Component;

@Component
public class UsersDtoConverter {

    public UsersEntity convertDtoToUsersClient(CreateUsersID inputData) {
        return new UsersEntity().setFirstName(inputData.getFirstName())
                .setLastName(inputData.getLastName())
                .setPhoneNumber(inputData.getPhoneNumber())
                .setEmail(inputData.getEmail())
                .setUserPassword(inputData.getUserPassword())
                .setUserRole(UserRole.CLIENT)
                .setUserStatus(UserStatus.ACTIVE);
    }

    public UsersEntity convertDtoToUsersAdministrator(CreateUsersID inputData) {
        return new UsersEntity().setFirstName(inputData.getFirstName())
                .setLastName(inputData.getLastName())
                .setPhoneNumber(inputData.getPhoneNumber())
                .setEmail(inputData.getEmail())
                .setUserPassword(inputData.getUserPassword())
                .setUserRole(UserRole.ADMINISTRATOR);
    }

    public UsersDto convertUsersToDto(UsersEntity user) {
        return new UsersDto().setId(user.getId())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setPhoneNumber(user.getPhoneNumber())
                .setEmail(user.getEmail())
                .setUserRole(user.getUserRole())
                .setUserStatus(user.getUserStatus());
    }
}
