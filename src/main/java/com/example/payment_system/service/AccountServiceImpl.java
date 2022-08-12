package com.example.payment_system.service;

import com.example.payment_system.dto.inputData.*;
import com.example.payment_system.dto.outData.CashAccountDto;
import com.example.payment_system.dto.outData.PutCurrencyToAccountRes;
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

        UsersEntity user = usersRepository.findByPhoneNumber(inputData.getPhoneNumber());
        if (user == null) {
            throw new GeneralAppException("Клиент с указанным номером телефона не существует");
        }

        CashAccountEntity savedCashAccount = accountRepository.save(new CashAccountEntity()
                .setAccountNumber(accountNumberGen.getCashAccountNumber(inputData.getCurrency()))
                .setCurrency(Currency.valueOf(inputData.getCurrency().toUpperCase()))
                .setCurrencyAmount(BigDecimal.ZERO)
                .setUser(user)
                .setStatus(CashAccountStatus.ACTIVE));

        return accountDtoConverter.convertCashAccountToDto(savedCashAccount);
    }

    @Transactional
    @Override
    public void blockCashAccount(BlockCashAccountID inputData) throws ValidationException, GeneralAppException {
        accountIDValidator.blockCashAccountIDValidate(inputData);

        CashAccountEntity account = accountRepository.findByAccountNumber(inputData.getCashAccountNumber());
        if (account == null) {
            throw new GeneralAppException("Указанный счет не существует");
        }

        account.setStatus(CashAccountStatus.BLOCKED);
        accountRepository.save(account);
    }

    @Transactional
    @Override
    public void unblockCashAccount(UnblockCashAccountID inputData)
            throws GeneralAppException, ValidationException {
        accountIDValidator.UnblockCashAccountIDValidate(inputData);

        CashAccountEntity account = accountRepository.findByAccountNumber(inputData.getCashAccountNumber());
        if (account == null) {
            throw new GeneralAppException("Указанный счет не существует");
        }
        if (account.getStatus().equals(CashAccountStatus.CLOSED)) {
            throw new GeneralAppException("Указанный счет закрыт");
        }
        if (account.getStatus().equals(CashAccountStatus.ACTIVE)) {
            throw new GeneralAppException("Указанный счет не был заблокирован");
        }
        account.setStatus(CashAccountStatus.ACTIVE);
        accountRepository.save(account);
    }

    @Transactional
    @Override
    public void closeCashAccount(CloseCashAccountID inputData) throws GeneralAppException,
            ValidationException {
        accountIDValidator.closeCashAccountIDValidate(inputData);

        CashAccountEntity account = accountRepository.findByAccountNumber(inputData.getCashAccountNumber());
        if (account == null) {
            throw new GeneralAppException("Указанный счет не существует");
        }
        if (!cashAccountIsEmpty(account)) {
            throw new GeneralAppException("На счете содежратся средства. Счет не может быть закрыт");
        }

        account.setStatus(CashAccountStatus.CLOSED);
        accountRepository.save(account);
    }

    @Transactional
    @Override
    public PutCurrencyToAccountRes putCurrencyToAccount(PutCurrencyToAccountID inputData)
            throws ValidationException, GeneralAppException {
        accountIDValidator.PutCashToAccountIDValidate(inputData);

        CashAccountEntity account = accountRepository.findByAccountNumber(inputData.getCashAccountNumber());
        if (account == null) {
            throw new GeneralAppException("Указанный счет не существует");
        }
        if (account.getStatus().equals(CashAccountStatus.BLOCKED)) {
            throw new GeneralAppException("Указанный счет заблокирован");
        }
        BigDecimal allCashAfter = account.getCurrencyAmount().add(new BigDecimal(inputData.getCurrencyAmount()));
        account.setCurrencyAmount(allCashAfter);
        accountRepository.save(account);

        return new PutCurrencyToAccountRes().setCurrencyAmount(inputData.getCurrencyAmount())
                .setCurrencyBalance(account.getCurrencyAmount().toString());
    }

    @Override
    public boolean cashAccountIsEmpty(CashAccountEntity accountEntity) {
        BigDecimal currencyAmount = accountEntity.getCurrencyAmount();
        return currencyAmount.compareTo(new BigDecimal("0")) == 0;
    }
}
