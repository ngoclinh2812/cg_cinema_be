package com.codegym.c11.controller;

import com.codegym.c11.model.dto.request.AccountRequestDto;
import com.codegym.c11.model.entity.Account;
import com.codegym.c11.service.IAccountService;
import com.codegym.c11.utils.AccountMapper;
import com.codegym.c11.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/test/account")
public class AccountController {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountRequestDto accountDto) {
        try {
            String token = accountService.login(accountDto);
            if (token != null) {
                return new ResponseEntity<>(token, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody AccountRequestDto accountRequestDto) {
        try {
            Account newAccount = accountMapper.mapperFromRequestDtoToEntity(accountRequestDto);
            accountService.saveNewAccount(newAccount);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
