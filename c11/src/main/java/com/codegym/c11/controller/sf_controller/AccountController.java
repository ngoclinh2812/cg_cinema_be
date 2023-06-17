package com.codegym.c11.controller.sf_controller;

import com.codegym.c11.exception.api.ResourceNotFoundException;
import com.codegym.c11.exception.api.ValidationException;
import com.codegym.c11.model.dto.request.AccountRequestDto;
import com.codegym.c11.model.dto.response.AccountResponseDto;
import com.codegym.c11.model.entity.Account;
import com.codegym.c11.service.sf.IAccountService;
import com.codegym.c11.utils.AccountMapper;
import com.codegym.c11.service.sf.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController(value = "sfAccountController")
@CrossOrigin(origins = "${app.cors.allowedOrigins}")
@RequestMapping("/api/sf/account")
public class AccountController {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(HttpServletRequest request) {
        try {
            String username = (String) request.getAttribute("username");

            if (username!= null) {
                Account account = accountService.findByUsername(username);

                if (account != null) {
                    AccountResponseDto accountDto = accountMapper.convertFromEntityToDto(account);
                    return new ResponseEntity<>(accountDto, HttpStatus.OK);
                }
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not get account.");
        }
    }

//    @GetMapping("/profile")
//    public ResponseEntity<?> getProfile(HttpServletRequest request) {
//        try {
//            String username = (String) request.getAttribute("username");
//            if (username != null) {
//                Account account = accountService.findByUsername(username);
//                if (account != null) {
//                    AccountResponseDto accountResponseDto = accountMapper.convertFromEntityToDto(account);
//                    return new ResponseEntity<>(accountResponseDto, HttpStatus.OK);
//                }
//            } else {
//                // Handle the case when the username attribute is not found
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
//            }
//        } catch (ResourceNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not get account.");
//        }
//        return null;
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountRequestDto accountDto) {
        try {
            String token = accountService.login(accountDto);
            if (token != null) {
                return new ResponseEntity<>(token, HttpStatus.OK);
            } else {
                throw new ResourceNotFoundException("Account not found");
            }
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody AccountRequestDto accountRequestDto) {
        try {
            Account newAccount = accountMapper.convertFromRequestDtoToEntity(accountRequestDto);
            boolean validateAccount = accountService.validateAccount(newAccount);

            if (validateAccount) {
                accountService.saveNewAccount(newAccount);
//                emailService.sendAccountConfirmEmail(newAccount.getEmail());
                return new ResponseEntity<>(newAccount, HttpStatus.OK);
            } else {
                throw new ValidationException("Invalid account");
            }
        } catch (ValidationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}
