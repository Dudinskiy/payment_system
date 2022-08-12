package com.example.payment_system.util.validator;

import com.example.payment_system.dto.inputData.ConfirmPaymentID;
import com.example.payment_system.dto.inputData.CreatePaymentID;
import com.example.payment_system.exception.ValidationException;
import com.example.payment_system.util.AccountNumberControlCode;
import com.example.payment_system.util.MOD97_10Code;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PaymentIDValidator extends IDValidator {
    public PaymentIDValidator(MOD97_10Code mod97_10Code,
                              AccountNumberControlCode numberControlCode) {
        super(mod97_10Code, numberControlCode);
    }

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
        message = checkCurrencyAmountField(inputData.getCurrencyAmount(), message);
        message = checkPayAccountNumberField(inputData.getPayAccountNumber(), message);
        message = checkBenefAccountNumberField(inputData.getBenefAccountNumber(), message);

        if (!message.isEmpty()) {
            throw new ValidationException(message);
        }
    }

    public String checkPayAccountNumberField(String field, String message) {
        if (field != null && !field.isEmpty()) {
            if (!getNumberControlCode().isValidAccountNumberNumeric(field)) {
                message = message + "Поле PayAccountNumber имеет неверное значение (BBAN)\n";
            }
            if (!getMod97_10Code().isValidIBanNumber(field)) {
                message = message + "Поле PayAccountNumber имеет неверное значение (IBAN) \n";
            }
        }
        return message;
    }

    public String checkBenefAccountNumberField(String field, String message) {
        if (field != null && !field.isEmpty()) {
            if (!getNumberControlCode().isValidAccountNumberNumeric(field)) {
                message = message + "Поле BenefAccountNumber имеет неверное значение (BBAN)\n";
            }
            if (!getMod97_10Code().isValidIBanNumber(field)) {
                message = message + "Поле BenefAccountNumber имеет неверное значение (IBAN)\n";
            }
        }
        return message;
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

    public boolean UUIDValidate(String uuid) {
        String regUUID = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";

        Pattern pattern = Pattern.compile(regUUID);
        Matcher matcher = pattern.matcher(uuid);

        return matcher.matches();
    }
}
