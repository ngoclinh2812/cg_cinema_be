package com.studentmanagement.linhoang.controller.test;

import com.studentmanagement.linhoang.model.dto.request.AccountRequestDto;
import com.studentmanagement.linhoang.model.dto.response.AccountResponseDto;
import com.studentmanagement.linhoang.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
@RequestMapping("/api/test/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

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
}
