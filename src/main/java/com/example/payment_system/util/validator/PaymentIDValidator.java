package com.example.payment_system.util.validator;

import com.example.payment_system.dto.inputData.ConfirmPaymentID;
import com.example.payment_system.dto.inputData.CreatePaymentID;
import com.example.payment_system.exception.ValidationException;
import com.example.payment_system.util.AccountNumberControlCode;
import com.example.payment_system.util.MOD97_10Code;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@AllArgsConstructor
public class PaymentIDValidator extends IDValidator {
    private final MOD97_10Code mod97_10Code;
    private final AccountNumberControlCode numberControlCode;

    public void createPaymentIDValidate(CreatePaymentID inputData) throws ValidationException {
        String message = "";

        if (inputData == null) {
            throw new ValidationException("Объект CreatePaymentID является null");
        }
        if (((inputData.getPayAccountNumber() == null || inputData.getPayAccountNumber().isEmpty()) &&
                (inputData.getBenefAccountNumber() == null || inputData.getBenefAccountNumber().isEmpty())) &&
                ((inputData.getPayCardNumber() == null || inputData.getPayCardNumber().isEmpty()) &&
                        (inputData.getBenefCardNumber() == null || inputData.getBenefCardNumber().isEmpty()))) {
            message = message + "Реквизиты отправителя и получателя платежа не заданы\n";
        }
        if ((inputData.getPayAccountNumber() == null || inputData.getPayAccountNumber().isEmpty()) &&
                (inputData.getPayCardNumber() == null || inputData.getPayCardNumber().isEmpty())) {
            message = message + "Реквизиты отправителя платежа не заданы\n";
        }
        if ((inputData.getBenefAccountNumber() == null || inputData.getBenefAccountNumber().isEmpty()) &&
                (inputData.getBenefCardNumber() == null || inputData.getBenefCardNumber().isEmpty())) {
            message = message + "Реквизиты получателя платежа не заданы\n";
        }
        if (inputData.getAmountPayment() == null || inputData.getAmountPayment().isEmpty()) {
            message = message + "Поле AmountPayment не содержит значения\n";
        }
        if (inputData.getAmountPayment() != null && !inputData.getAmountPayment().isEmpty()) {
            if (!isNumberAndHasRequiredAccuracy(inputData.getAmountPayment())) {
                message = message + "Поле AmountPayment не является числом или имеет недопустимое значение";

            } else if (lessThanMinAmount(inputData.getAmountPayment())) {
                message = message + "Сумма платежа не может быть меньше чем 0.01\n";
            }
        }
        if (inputData.getPayAccountNumber() != null && !inputData.getPayAccountNumber().isEmpty()) {
            if (!numberControlCode.isValidAccountNumberNumeric(inputData.getPayAccountNumber())) {
                message = message + "Поле PayAccountNumber имеет неверное значение\n";
            }
        }
        if (inputData.getBenefAccountNumber() != null && !inputData.getBenefAccountNumber().isEmpty()) {
            if (!numberControlCode.isValidAccountNumberNumeric(inputData.getBenefAccountNumber())) {
                message = message + "Поле BeneficAccountNumber имеет неверное значение\n";
            }
        }
        if (inputData.getPayAccountNumber() != null && !inputData.getPayAccountNumber().isEmpty()) {
            if (!mod97_10Code.isValidIBanNumber(inputData.getPayAccountNumber())) {
                message = message + "Поле PayAccountNumber имеет неверное значение\n";
            }
        }
        if (inputData.getBenefAccountNumber() != null && !inputData.getBenefAccountNumber().isEmpty()) {
            if (!mod97_10Code.isValidIBanNumber(inputData.getBenefCardNumber())) {
                message = message + "Поле BeneficAccountNumber имеет неверное значение\n";
            }
        }

        if (!message.isEmpty()) {
            throw new ValidationException(message);
        }
    }

    public void confirmPaymentIDValidate(ConfirmPaymentID inputData) throws ValidationException {
        String message = "";

        if (inputData == null) {
            throw new ValidationException("Объект ConfirmPaymentID является null");
        }
        if (inputData.getPaymentId() == null || inputData.getPaymentId().isEmpty()) {
            message = message + "Поле PaymentId не содержит значения\n";
        }
        if (inputData.getPaymentId() != null && !inputData.getPaymentId().isEmpty()) {
            if (!UUIDValidate(inputData.getPaymentId())) {
                message = message + "Значение поля PaymentId не является UUID\n";
            }
        }
        if (inputData.getPassword() == null || inputData.getPassword().isEmpty()) {
            message = message + "Поле Password не содержит значения\n";
        }

        if (!message.isEmpty()) {
            throw new ValidationException(message);
        }
    }

    private boolean lessThanMinAmount(String payAmount) {
        int compareRes = new BigDecimal(payAmount).compareTo(new BigDecimal("0.01"));

        return compareRes < 0;
    }

    private boolean isNumberAndHasRequiredAccuracy(String number) {
        String regForNumber = "^([1-9]\\d*\\.\\d{1,2}|0\\.\\d{1,2}|0|[1-9]\\d+|[1-9])$";

        Pattern pattern = Pattern.compile(regForNumber);
        Matcher matcher = pattern.matcher(number);

        return matcher.matches();
    }
}
