package com.example.payment_system.service;

import com.example.payment_system.dto.outData.CardDto;
import com.example.payment_system.dto.inputData.BlockDeleteCardID;
import com.example.payment_system.dto.inputData.CreateCardID;
import com.example.payment_system.entity.CashAccountEntity;
import com.example.payment_system.entity.CardEntity;
import com.example.payment_system.enums.CardStatus;
import com.example.payment_system.enums.CardType;
import com.example.payment_system.exception.GeneralAppException;
import com.example.payment_system.exception.ValidationException;
import com.example.payment_system.repository.CashAccountRepository;
import com.example.payment_system.repository.CardRepository;
import com.example.payment_system.util.PasswordGen;
import com.example.payment_system.util.converter.CardConverter;
import com.example.payment_system.util.CardNumberGen;
import com.example.payment_system.util.validator.CardIDValidator;
import com.example.payment_system.util.validator.UsersIDValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CashAccountRepository accountRepository;
    private final CardIDValidator cardIDValidator;
    private final CardConverter cardConverter;
    private final CardNumberGen cardNumber;
    private final PasswordGen password;


    @Transactional
    @Override
    public CardDto createCard(CreateCardID inputData) throws ValidationException, GeneralAppException {
        cardIDValidator.createCardIDValidate(inputData);

        CardStatus cardStatus = CardStatus.INACTIVE;
        CashAccountEntity cashAccount = null;

        if (inputData.getCashAccountNumber() != null || !inputData.getCashAccountNumber().isEmpty()) {
            cashAccount = accountRepository.findByAccountNumber(inputData.getCashAccountNumber());
            cardStatus = CardStatus.VALID;
            if (cashAccount == null) {
                throw new GeneralAppException("Счет с указанным номером не существует");
            }
        }
        CardEntity savedCard = cardRepository.save(new CardEntity()
                .setCardNumber(cardNumber.getCardNumber(inputData))
                .setCardType(CardType.valueOf(inputData.getCardType()))
                .setValidityDate(getValidityDate())
                .setPinCode(password.getPassword(4))
                .setCvvCode(password.getPassword(3))
                .setCashAccount(cashAccount)
                .setStatus(cardStatus)
        );
        return cardConverter.convertCardToDto(savedCard);
    }

    @Transactional
    @Override
    public void blockCard(BlockDeleteCardID inputData) throws ValidationException, GeneralAppException {
        cardIDValidator.blockDeleteCardIDValidate(inputData);

        CardEntity card = cardRepository.findByCardNumber(inputData.getCardNumber());
        if (card == null) {
            throw new GeneralAppException("Карта с указанным номером не существует");
        }

        cardRepository.save(card.setStatus(CardStatus.BLOCKED));
    }

    @Transactional
    @Override
    public void deleteCard(BlockDeleteCardID inputData) throws ValidationException, GeneralAppException {
        cardIDValidator.blockDeleteCardIDValidate(inputData);

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

    public LocalDateTime getValidityDate() {
        return LocalDateTime.now().plusMonths(24);
    }

}
