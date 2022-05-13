package com.example.payment_system.util.validator;

public class IDValidator {

    public boolean UUIDValidate(String uuid) {
        String regUUID = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";
        return uuid.matches(regUUID);
    }
}
