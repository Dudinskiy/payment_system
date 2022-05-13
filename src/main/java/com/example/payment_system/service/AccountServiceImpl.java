package com.example.payment_system.service;

import com.example.payment_system.dto.outData.CashAccountDto;
import com.example.payment_system.dto.inputData.BlockCashAccountID;
import com.example.payment_system.dto.inputData.CreateCashAccountID;
import com.example.payment_system.entity.CashAccountEntity;
import com.example.payment_system.entity.UsersEntity;
import com.example.payment_system.enums.CashAccountStatus;
import com.example.payment_system.enums.Currency;
import com.example.payment_system.exception.GeneralAppException;
import com.example.payment_system.exception.ValidationException;
import com.example.payment_system.repository.CashAccountRepository;
import com.example.payment_system.repository.UsersRepository;
import com.example.payment_system.util.CashAccountNumberGen;
import com.example.payment_system.util.converter.CashAccountDtoConverter;
import com.example.payment_system.util.validator.CashAccountIDValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UsersRepository usersRepository;
    private final CashAccountRepository accountRepository;
    private final CashAccountIDValidator accountIDValidator;
    private final CashAccountDtoConverter accountDtoConverter;
    private final CashAccountNumberGen accountNumberGen;

    @Transactional
    @Override
    public CashAccountDto createCashAccount(CreateCashAccountID inputData) throws GeneralAppException, ValidationException {
        accountIDValidator.createCashAccountIDValidate(inputData);

        UsersEntity user;
        Optional<UsersEntity> optional = usersRepository.findById(UUID.fromString(inputData.getUserId()));
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new GeneralAppException("Указанный клиент не существует");
        }

        CashAccountEntity savedCashAccount = accountRepository.save(new CashAccountEntity()
                .setAccountNumber(accountNumberGen.getCashAccountNumber(inputData))
                .setCurrency(Currency.valueOf(inputData.getCurrency().toUpperCase()))
                .setAmountCurrency(BigDecimal.ZERO)
                .setUser(user)
                .setStatus(CashAccountStatus.VALID));

        return accountDtoConverter.convertCashAccountToDto(savedCashAccount);
    }

    @Transactional
    @Override
    public void blockCashAccount(BlockCashAccountID inputData) throws ValidationException, GeneralAppException {
        accountIDValidator.blockCashAccountIDValidate(inputData);

        CashAccountEntity account = accountRepository.findByAccountNumber(inputData.getAccountNumber());
        if (account == null) {
            throw new GeneralAppException("Указанный счет не существует");
        }

        account.setStatus(CashAccountStatus.BLOCKED);
        accountRepository.save(account);
    }

    @Override
    public void deleteCashAccount(BlockCashAccountID user) {

    }

    @Override
    public boolean cashAccountIsEmpty(CashAccountEntity accountEntity) {
        BigDecimal currencyAmount = accountEntity.getAmountCurrency();
        return currencyAmount.compareTo(new BigDecimal("0")) == 0;
    }
}
