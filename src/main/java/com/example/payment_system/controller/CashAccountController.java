package com.example.payment_system.controller;

import com.example.payment_system.dto.inputData.*;
import com.example.payment_system.dto.outData.CashAccountDto;
import com.example.payment_system.dto.outData.PutCurrencyToAccountRes;
import com.example.payment_system.exception.GeneralAppException;
import com.example.payment_system.exception.ValidationException;
import com.example.payment_system.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/payment-system/cash-account")
public class CashAccountController {
    private final AccountService accountService;

    @PostMapping("/create")
    public CashAccountDto createCashAccount(@RequestBody CreateCashAccountID inputData)
            throws GeneralAppException, ValidationException {
        return accountService.createCashAccount(inputData);
    }

    @PostMapping("/block")
    public ResponseEntity<String> blockCashAccount(@RequestBody BlockCashAccountID inputData)
            throws ValidationException, GeneralAppException {
        accountService.blockCashAccount(inputData);
        return new ResponseEntity<>("Счет заблокирован", HttpStatus.OK);
    }

    @PostMapping("/unblock")
    public ResponseEntity<String> unblockCashAccount(@RequestBody UnblockCashAccountID inputData)
            throws ValidationException, GeneralAppException {
        accountService.unblockCashAccount(inputData);
        return new ResponseEntity<>("Счет разблокирован", HttpStatus.OK);
    }


    @PostMapping("/close")
    public ResponseEntity<String> closeCashAccount(@RequestBody CloseCashAccountID inputData)
            throws ValidationException, GeneralAppException {
        accountService.closeCashAccount(inputData);
        return new ResponseEntity<>("Счет закрыт", HttpStatus.OK);
    }

    @PostMapping("/put")
    public PutCurrencyToAccountRes putCurrencyToAccount(@RequestBody PutCurrencyToAccountID inputData)
            throws ValidationException, GeneralAppException {
        return accountService.putCurrencyToAccount(inputData);
    }
}
