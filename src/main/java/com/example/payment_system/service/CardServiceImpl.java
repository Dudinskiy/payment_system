package com.example.payment_system.service;

import com.example.payment_system.dto.inputData.*;
import com.example.payment_system.dto.outData.CardDto;
import com.example.payment_system.entity.CardEntity;
import com.example.payment_system.entity.CashAccountEntity;
import com.example.payment_system.entity.UsersEntity;
import com.example.payment_system.enums.CardStatus;
import com.example.payment_system.enums.CardType;
import com.example.payment_system.enums.CashAccountStatus;
import com.example.payment_system.enums.Currency;
import com.example.payment_system.exception.GeneralAppException;
import com.example.payment_system.exception.ValidationException;
import com.example.payment_system.repository.CashAccountRepository;
import com.example.payment_system.repository.CardRepository;
import com.example.payment_system.repository.UsersRepository;
import com.example.payment_system.util.CashAccountNumberGen;
import com.example.payment_system.util.PasswordGen;
import com.example.payment_system.util.converter.CardConverter;
import com.example.payment_system.util.CardNumberGen;
import com.example.payment_system.util.validator.CardIDValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CashAccountRepository accountRepository;
    private final UsersRepository usersRepository;
    private final CardIDValidator cardIDValidator;
    private final CardConverter cardConverter;
    private final CardNumberGen cardNumberGen;
    private final CashAccountNumberGen accountNumberGen;
    private final PasswordGen password;
    private static final int TIME_OF_ACTION = 24;


    @Transactional
    @Override
    public CardDto createCard(CreateCardID inputData) throws ValidationException {
        cardIDValidator.createCardIDValidate(inputData);

        String cardNumber = getCardNumber(inputData);

        CardEntity savedCard = cardRepository.save(new CardEntity()
                .setCardNumber(cardNumber)
                .setCardType(CardType.valueOf(inputData.getCardType().toUpperCase()))
                .setValidityDate(LocalDateTime.now())
                .setPinCode(password.getPassword(4))
                .setCvvCode(password.getPassword(3))
                .setCashAccount(null)
                .setStatus(CardStatus.INACTIVE)
        );
        return cardConverter.convertCardToDto(savedCard);
    }

    @Transactional
    @Override
    public void blockCard(BlockCardID inputData) throws ValidationException, GeneralAppException {
        cardIDValidator.blockCardIDValidate(inputData);

        CardEntity card = cardRepository.findByCardNumber(inputData.getCardNumber());
        if (card == null) {
            throw new GeneralAppException("Карта с указанным номером не существует");
        }

        cardRepository.save(card.setStatus(CardStatus.BLOCKED));
    }

    @Transactional
    @Override
    public void closeCard(CloseCardID inputData) throws ValidationException, GeneralAppException {
        cardIDValidator.closeCardIDValidate(inputData);

        CardEntity card = cardRepository.findByCardNumber(inputData.getCardNumber());
        if (card == null) {
            throw new GeneralAppException("Карта с указанным номером не существует");
        }

        CardStatus status = card.getStatus();
        if (status.equals(CardStatus.INACTIVE)) {
            cardRepository.delete(card);
        } else {
            cardRepository.save(card.setStatus(CardStatus.CLOSED));
        }
    }

    @Transactional
    @Override
    public CardDto getCard(GetCardID inputData) throws ValidationException, GeneralAppException {
        cardIDValidator.getCardIDValidate(inputData);

        UsersEntity user = usersRepository.findByPhoneNumber(inputData.getPhoneNumber());
        if (user == null) {
            throw new GeneralAppException("Клиент с указанным номером телефона не существует");
        }
        CashAccountEntity cashAccount = accountRepository.save(new CashAccountEntity()
                .setAccountNumber(accountNumberGen.getCashAccountNumber(inputData.getCurrency()))
                .setCurrency(Currency.valueOf(inputData.getCurrency().toUpperCase()))
                .setCurrencyAmount(BigDecimal.ZERO)
                .setUser(user)
                .setStatus(CashAccountStatus.ACTIVE));

        CardEntity newCard = getNewCard(CardType.valueOf(inputData.getCardType().toUpperCase()));

        CardEntity savedCard = cardRepository.save(newCard.setCashAccount(cashAccount)
                .setStatus(CardStatus.ACTIVE)
                .setValidityDate(getValidityDate()));

        return cardConverter.convertCardToDto(savedCard);
    }

    @Transactional
    @Override
    public CardDto getCardForAccount(GetCardForAccountID inputData)
            throws ValidationException, GeneralAppException {
        cardIDValidator.getCardForAccountIDValidate(inputData);

        CashAccountEntity cashAccount = accountRepository.findByAccountNumber(inputData.getCashAccountNumber());
        if (cashAccount == null) {
            throw new GeneralAppException("Счет с указанным номером не существует");
        }
        if (cashAccount.getStatus().equals(CashAccountStatus.BLOCKED)) {
            throw new GeneralAppException("Счет с указанным номером не заблокирован");
        }
        if (cashAccount.getStatus().equals(CashAccountStatus.CLOSED)) {
            throw new GeneralAppException("Счет с указанным номером не закрыт");
        }

        CardEntity newCard = getNewCard(CardType.valueOf(inputData.getCardType().toUpperCase()));

        CardEntity savedCard = cardRepository.save(newCard.setCashAccount(cashAccount)
                .setStatus(CardStatus.ACTIVE)
                .setValidityDate(getValidityDate()));

        return cardConverter.convertCardToDto(savedCard);
    }

    private LocalDateTime getValidityDate() {
        return LocalDateTime.now().plusMonths(TIME_OF_ACTION);
    }

    private String getCardNumber(CreateCardID inputData) {
        String cardNumber = cardNumberGen.getCardNumber(inputData);
        CardEntity cardEntity = cardRepository.findByCardNumber(cardNumber);
        if (cardEntity != null) {
            getCardNumber(inputData);
        }
        return cardNumber;
    }

    private CardEntity getNewCard(CardType type) throws GeneralAppException {
        CardEntity newCard = cardRepository.findByCardTypeAndStatus(type, CardStatus.INACTIVE);
        if (newCard == null) {
            throw new GeneralAppException("Нет в наличии карт для выпуска");
        }
        return newCard;
    }

}
