package com.example.payment_system.service;

import com.example.payment_system.dto.outData.UsersDto;
import com.example.payment_system.dto.inputData.CreateUsersID;
import com.example.payment_system.dto.inputData.DeleteUserID;
import com.example.payment_system.entity.CashAccountEntity;
import com.example.payment_system.entity.UsersEntity;
import com.example.payment_system.enums.UserStatus;
import com.example.payment_system.exception.GeneralAppException;
import com.example.payment_system.exception.ValidationException;
import com.example.payment_system.repository.CashAccountRepository;
import com.example.payment_system.repository.UsersRepository;
import com.example.payment_system.util.converter.UsersDtoConverter;
import com.example.payment_system.util.validator.UsersIDValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final CashAccountRepository accountRepository;
    private final AccountService accountService;
    private final UsersDtoConverter usersDtoConverter;
    private final UsersIDValidator usersIDValidator;


    @Transactional
    @Override
    public UsersDto createClient(CreateUsersID inputData) throws ValidationException {
        usersIDValidator.createUsersIDValidate(inputData);

        UsersEntity savedUsers = usersRepository.save(usersDtoConverter.convertDtoToUsersClient(inputData));

        return usersDtoConverter.convertUsersToDto(savedUsers);
    }

    @Override
    public UsersDto createAdministrator(CreateUsersID inputData) {
        UsersEntity savedUsers = usersRepository.save(usersDtoConverter.convertDtoToUsersAdministrator(inputData));

        return usersDtoConverter.convertUsersToDto(savedUsers);
    }

    @Transactional
    @Override
    public void deleteClientByPhone(DeleteUserID inputData) throws GeneralAppException, ValidationException {
        usersIDValidator.deleteUserIDValidate(inputData);

        UsersEntity user = usersRepository.findByPhoneNumber(inputData.getPhoneNumber());
        if (user == null) {
            throw new GeneralAppException("Клиент с указанным номером телефона не существует");
        }
        List<CashAccountEntity> allAccounts = accountRepository.findAllByUser(user);
        if (!allAccounts.isEmpty()) {
            for (CashAccountEntity accountEntity : allAccounts) {
                if (accountService.cashAccountIsEmpty(accountEntity)) {
                    String accountNumber = accountEntity.getAccountNumber();
                    throw new GeneralAppException("Клиент не удален. " +
                            "На счете номер: " + accountNumber + " содержатся средства");
                }
            }
        }
        user.setUserStatus(UserStatus.DELETED)
                .setPhoneNumber(user.getPhoneNumber() + "_uuid:" + user.getId())
                .setEmail(user.getEmail() + "_uuid:" + user.getId());
        usersRepository.save(user);
    }
}
