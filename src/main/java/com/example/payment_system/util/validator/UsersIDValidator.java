package com.example.payment_system.util.validator;

import com.example.payment_system.dto.inputData.*;
import com.example.payment_system.exception.ValidationException;
import com.example.payment_system.util.AccountNumberControlCode;
import com.example.payment_system.util.MOD97_10Code;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UsersIDValidator extends IDValidator {
    public UsersIDValidator(MOD97_10Code mod97_10Code,
                            AccountNumberControlCode numberControlCode) {
        super(mod97_10Code, numberControlCode);
    }

    public void createUsersIDValidate(CreateUsersID inputData) throws ValidationException {
        String message = "";

        if (inputData == null) {
            throw new ValidationException("Объект CreateUsersID является null");
        }
        message = checkFirstNameField(inputData.getFirstName(), message);
        message = checkLastNameField(inputData.getLastName(), message);
        message = checkPhoneNumberField(inputData.getPhoneNumber(), message);
        message = checkEmailField(inputData.getEmail(), message);
        message = checkUserPasswordField(inputData.getUserPassword(), message);

        if (!message.isEmpty()) {
            throw new ValidationException(message);
        }
    }

    public void deleteUserIDValidate(DeleteUserID inputData) throws ValidationException {
        String message = "";

        if (inputData == null) {
            throw new ValidationException("Объект DeleteUserID является null");
        }
        message = checkPhoneNumberField(inputData.getPhoneNumber(), message);

        if (!message.isEmpty()) {
            throw new ValidationException(message);
        }
    }

    public String checkFirstNameField(String field, String message) {
        if (field == null || field.isEmpty()) {
            message = message + "Поле FirstName не содержит значения\n";
        }
        return message;
    }

    public String checkLastNameField(String field, String message) {
        if (field == null || field.isEmpty()) {
            message = message + "Поле LastName не содержит значения\n";
        }
        return message;
    }

    public String checkEmailField(String field, String message) {
        if (field == null || field.isEmpty()) {
            message = message + "Поле Email не содержит значения\n";
        }
        if (field != null && !field.isEmpty()) {
            if (!emailValidate(field)) {
                message = message + "Поле Email имеет неверное значение\n";
            }
        }
        return message;
    }

    public String checkUserPasswordField(String field, String message) {
        if (field == null || field.isEmpty()) {
            message = message + "Значение поля Password не содержит значения\n";
        }
        if (field != null && !field.isEmpty()) {
            if (!passwordValidate(field)) {
                message = message + "Значение поля Password не отвечает требованиям\n";
            }
        }
        return message;
    }

    public boolean emailValidate(String email) {
        String regForEmail = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";

        Pattern pattern = Pattern.compile(regForEmail);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public boolean passwordValidate(String password) {
        //String regForPassword = "^.*(?=.{6,})(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?!.* )$";
        String regForPassword = "^[a-zA-Z0-9]{6,12}$";
        Pattern pattern = Pattern.compile(regForPassword);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }
}
