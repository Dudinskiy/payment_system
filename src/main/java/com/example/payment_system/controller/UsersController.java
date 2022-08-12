package com.example.payment_system.controller;

import com.example.payment_system.dto.inputData.CreateUsersID;
import com.example.payment_system.dto.inputData.DeleteUserID;
import com.example.payment_system.dto.outData.UsersDto;
import com.example.payment_system.exception.GeneralAppException;
import com.example.payment_system.exception.ValidationException;
import com.example.payment_system.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/payment-system/users")
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/create-client")
    public UsersDto createClient(@RequestBody CreateUsersID inputData) throws ValidationException {
        return usersService.createClient(inputData);
    }

    @PostMapping("/delete-client")
    public ResponseEntity<String> deleteClientByPhone(@RequestBody DeleteUserID inputData)
            throws GeneralAppException, ValidationException {
        usersService.deleteClientByPhone(inputData);
        return new ResponseEntity<>("Клиент удален", HttpStatus.OK);
    }
}
