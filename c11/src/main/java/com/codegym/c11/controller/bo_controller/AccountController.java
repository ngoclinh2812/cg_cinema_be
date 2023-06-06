package com.codegym.c11.controller.bo_controller;

import com.codegym.c11.model.dto.request.AccountRequestDto;
import com.codegym.c11.service.sf.IAccountService;
import com.codegym.c11.utils.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "boAccountController")
@CrossOrigin(origins = "${app.cors.allowedOrigins}")
@RequestMapping("/api/bo/account")
public class AccountController {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private IAccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountRequestDto accountDto) {
        try {
            String token = accountService.loginAsAdmin(accountDto);
            if (token != null) {
                return new ResponseEntity<>(token, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
