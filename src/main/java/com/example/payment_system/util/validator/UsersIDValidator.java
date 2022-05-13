package com.example.payment_system.util.validator;

import com.example.payment_system.dto.inputData.*;
import com.example.payment_system.enums.CardType;
import com.example.payment_system.enums.Currency;
import com.example.payment_system.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UsersIDValidator {

    public void createUsersIDValidate(CreateUsersID inputData) throws ValidationException {
        String message = "";

        if (inputData == null) {
            throw new ValidationException("Объект CreateUsersID является null");
        }
        if (inputData.getFirstName() == null || inputData.getFirstName().isEmpty()) {
            message = message + "Поле FirstName не содержит значения\n";
        }
        if (inputData.getLastName() == null || inputData.getLastName().isEmpty()) {
            message = message + "Поле LastName не содержит значения\n";
        }
        if (inputData.getPhoneNumber() == null || inputData.getPhoneNumber().isEmpty()) {
            message = message + "Поле PhoneNumber не содержит значения\n";
        }
        if (inputData.getPhoneNumber() != null && !inputData.getPhoneNumber().isEmpty()) {
            if (!phoneValidate(inputData.getPhoneNumber())) {
                message = message + "Поле PhoneNumber имеет неверное значение\n";
            }
        }
        if (inputData.getEmail() == null || inputData.getEmail().isEmpty()) {
            message = message + "Поле Email не содержит значения\n";
        }
        if (inputData.getEmail() != null && !inputData.getEmail().isEmpty()) {
            if (!emailValidate(inputData.getEmail())) {
                message = message + "Поле Email имеет неверное значение\n";
            }
        }
        if (inputData.getUserPassword() == null || inputData.getUserPassword().isEmpty()) {
            message = message + "Значение поля Password не содержит значения\n";
        }
        if (inputData.getUserPassword() != null && !inputData.getUserPassword().isEmpty()) {
            if (!passwordValidate(inputData.getUserPassword())) {
                message = message + "Значение поля Password не отвечает требованиям\n";
            }
        }

        if (!message.isEmpty()) {
            throw new ValidationException(message);
        }
    }

    public boolean phoneValidate(String phone) {
        String regForPhone = "^((\\+[\\- ]?38)[\\- ]?)?(\\(?(039)?(067)?(068)?(096)?(097)?" +
                "(098)?(050)?(066)?(095)?(099)?(063)?(073)?(093)?" +
                "(091)?(092)?(089)?(094)?\\)?[\\- ]?)?[\\d\\- ]{7,9}$";

        Pattern pattern = Pattern.compile(regForPhone);
        Matcher matcher = pattern.matcher(phone);

        return matcher.matches();
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
