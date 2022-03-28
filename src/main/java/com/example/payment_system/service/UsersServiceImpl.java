package com.example.payment_system.service;

import com.example.payment_system.dto.UsersDto;
import com.example.payment_system.dto.inputData.CreateUserInputData;
import com.example.payment_system.entity.Users;
import com.example.payment_system.repository.UsersRepository;
import com.example.payment_system.util.UsersDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final UsersDtoConverter usersDtoConverter;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, UsersDtoConverter usersDtoConverter) {
        this.usersRepository = usersRepository;
        this.usersDtoConverter = usersDtoConverter;
    }


    @Override
    public UsersDto createClient(CreateUserInputData createUserInputData) {
        Users savedUsers = usersRepository.save(usersDtoConverter.convertDtoToUsersClient(createUserInputData));

        return usersDtoConverter.convertUsersToDto(savedUsers);
    }

    @Override
    public UsersDto createAdministrator(CreateUserInputData createUserInputData) {
        Users savedUsers = usersRepository.save(usersDtoConverter.convertDtoToUsersAdministrator(createUserInputData));

        return usersDtoConverter.convertUsersToDto(savedUsers);
    }

    @Override
    public void deleteClient() {

    }
}
