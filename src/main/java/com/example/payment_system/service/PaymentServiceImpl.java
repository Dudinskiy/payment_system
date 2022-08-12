package com.example.payment_system.service;

import com.example.payment_system.dto.outData.ConfirmPaymentRes;
import com.example.payment_system.dto.outData.PaymentDto;
import com.example.payment_system.dto.inputData.ConfirmPaymentID;
import com.example.payment_system.dto.inputData.CreatePaymentID;
import com.example.payment_system.entity.CardEntity;
import com.example.payment_system.entity.CashAccountEntity;
import com.example.payment_system.entity.PaymentEntity;
import com.example.payment_system.entity.UsersEntity;
import com.example.payment_system.enums.CardStatus;
import com.example.payment_system.enums.CashAccountStatus;
import com.example.payment_system.enums.PaymentStatus;
import com.example.payment_system.exception.GeneralAppException;
import com.example.payment_system.exception.ValidationException;
import com.example.payment_system.repository.CardRepository;
import com.example.payment_system.repository.CashAccountRepository;
import com.example.payment_system.repository.PaymentRepository;
import com.example.payment_system.util.LuhnCode;
import com.example.payment_system.util.PasswordGen;
import com.example.payment_system.util.converter.PaymentDtoConverter;
import com.example.payment_system.util.validator.PaymentIDValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRep;
    private final CashAccountRepository accountRep;
    private final CardRepository cardRepository;
    private final PaymentIDValidator paymentIDValidator;
    private final PasswordGen password;
    private final PaymentDtoConverter converter;
    private final LuhnCode luhnCode;

    @Transactional
    @Override
    public PaymentDto createPayment(CreatePaymentID inputData) throws ValidationException, GeneralAppException {
        paymentIDValidator.createPaymentIDValidate(inputData);

        CashAccountEntity payAccount;
        CashAccountEntity benefAccount;
        CardEntity payCard;
        CardEntity benefCard;
        BigDecimal payAllCashBefore;
        int compareRes;

        if (inputData.getBenefCardNumber() == null || inputData.getBenefCardNumber().isEmpty() &&
                inputData.getPayCardNumber() == null || inputData.getPayCardNumber().isEmpty()) {
            payAccount = accountRep.findByAccountNumber(inputData.getPayAccountNumber());
            benefAccount = accountRep.findByAccountNumber(inputData.getBenefAccountNumber());

        } else if (inputData.getPayAccountNumber() == null || inputData.getPayAccountNumber().isEmpty() &&
                inputData.getBenefAccountNumber() == null || inputData.getBenefAccountNumber().isEmpty()) {
            throw new GeneralAppException("Не указаны реквизиты отправителя/получателя");

        } else {
            payCard = cardRepository.findByCardNumber(inputData.getPayCardNumber());
            if (payCard == null) {
                throw new GeneralAppException("Указанная карта отправителя не существует");
            }
            if (!luhnCode.isValidLuhn(inputData.getPayCardNumber())) {
                throw new GeneralAppException("Номер карты отправителя введен неверно");
            }
            if (payCard.getStatus().equals(CardStatus.INACTIVE)) {
                throw new GeneralAppException("Указанная карта не активна");
            }
            if (payCard.getStatus().equals(CardStatus.BLOCKED)) {
                throw new GeneralAppException("Указанная карта отправителя заблокирована");
            }
            if (payCard.getStatus().equals(CardStatus.CLOSED)) {
                throw new GeneralAppException("Указанная карта отправителя закрыта");
            }

            benefCard = cardRepository.findByCardNumber(inputData.getBenefCardNumber());
            if (benefCard == null) {
                throw new GeneralAppException("Указанная карта получателя не существует");
            }
            if (!luhnCode.isValidLuhn(inputData.getBenefCardNumber())) {
                throw new GeneralAppException("Номер карты получателя введен неверно");
            }
            if (benefCard.getStatus().equals(CardStatus.INACTIVE)) {
                throw new GeneralAppException("Указанная карта не активна");
            }
            if (benefCard.getStatus().equals(CardStatus.BLOCKED)) {
                throw new GeneralAppException("Указанная карта получателя заблокирована");
            }
            if (benefCard.getStatus().equals(CardStatus.CLOSED)) {
                throw new GeneralAppException("Указанная карта получателя закрыта");
            }
            payAccount = payCard.getCashAccount();
            benefAccount = benefCard.getCashAccount();
        }

        if (payAccount == null) {
            throw new GeneralAppException("Указанный счет отправителя не существует");
        }
        if (payAccount.getStatus().equals(CashAccountStatus.BLOCKED)) {
            throw new GeneralAppException("Указанный счет отправителя заблокирован");
        }
        if (payAccount.getStatus().equals(CashAccountStatus.CLOSED)) {
            throw new GeneralAppException("Указанный счет отправителя закрыт");
        }

        if (benefAccount == null) {
            throw new GeneralAppException("Указанный счет получателя не существует");
        }
        if (benefAccount.getStatus().equals(CashAccountStatus.BLOCKED)) {
            throw new GeneralAppException("Указанный счет получателя заблокирован");
        }
        if (benefAccount.getStatus().equals(CashAccountStatus.CLOSED)) {
            throw new GeneralAppException("Указанный счет получателя закрыт");
        }

        payAllCashBefore = payAccount.getCurrencyAmount();
        compareRes = payAllCashBefore.compareTo(new BigDecimal(inputData.getCurrencyAmount()));
        if (compareRes == -1) {
            throw new GeneralAppException("Недостаточно средств на счете");
        }

        PaymentEntity savedPayment = paymentRep.save(new PaymentEntity()
                .setPayAccount(payAccount)
                .setBeneficAccount(benefAccount)
                .setAmountPayment(new BigDecimal(inputData.getCurrencyAmount()))
                .setOtpPassword(password.getPassword(4))
                .setStatus(PaymentStatus.NEW));

        return converter.convertPaymentToDto(savedPayment);
    }


    @Transactional
    @Override
    public ConfirmPaymentRes confirmPayment(ConfirmPaymentID inputData) throws ValidationException, GeneralAppException {
        paymentIDValidator.confirmPaymentIDValidate(inputData);

        PaymentEntity payment;
        BigDecimal payAllCashAfter;
        BigDecimal benefAllCashAfter;

        Optional<PaymentEntity> optional = paymentRep.findById(UUID.fromString(inputData.getPaymentId()));
        if (optional.isPresent()) {
            payment = optional.get();
        } else {
            throw new GeneralAppException("Указанный платеж не существует");
        }

        if (inputData.getPassword().equals(payment.getOtpPassword())) {
            CashAccountEntity payAccount = accountRep.findByAccountNumber(payment.getPayAccount().getAccountNumber());
            payAllCashAfter = payAccount.getCurrencyAmount().subtract(payment.getAmountPayment());
            payAccount.setCurrencyAmount(payAllCashAfter);
            accountRep.save(payAccount);

            //Надо ввести проверку на блокировку, доступность счета получателя
            CashAccountEntity benefAccount = accountRep.findByAccountNumber(payment.getBeneficAccount().getAccountNumber());
            benefAllCashAfter = benefAccount.getCurrencyAmount().add(payment.getAmountPayment());
            benefAccount.setCurrencyAmount(benefAllCashAfter);
            accountRep.save(benefAccount);

            payment.setStatus(PaymentStatus.SUCCESSFUL);
            paymentRep.save(payment);

            return new ConfirmPaymentRes().setMessage("Платеж проведен успешно");

        } else {
            payment.setStatus(PaymentStatus.CANCELED);
            paymentRep.save(payment);

            return new ConfirmPaymentRes().setMessage("Неверный пароль. Платеж отменен");
        }
    }


    @Override
    public List<PaymentEntity> findAllUserPayment(UsersEntity user) {
        return null;
    }
}
