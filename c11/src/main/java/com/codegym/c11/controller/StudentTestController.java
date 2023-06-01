//package com.codegym.c11.controller;
//
//import com.studentmanagement.linhoang.model.dto.request.AccountRequestDto;
//import com.studentmanagement.linhoang.model.dto.response.AccountResponseDto;
//import com.studentmanagement.linhoang.model.entity.Account;
//import com.studentmanagement.linhoang.service.impl.AccountServiceImpl;
//import com.studentmanagement.linhoang.utils.AccountMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@CrossOrigin(origins = "${app.cors.allowedOrigins}")
//@RequestMapping("/api/test/student")
//public class StudentTestController {
//
//    @Autowired
//    private AccountServiceImpl accountService;
//
//    @Autowired
//    private AccountMapper accountMapper;
//
//    @GetMapping()
//    public ResponseEntity<?> testHello() {
//        return new ResponseEntity<>("Hello student", HttpStatus.OK);
//    }
//
//    @GetMapping("/{username}")
//    public ResponseEntity<?> getStudentInfo(@PathVariable("username") String username) {
//        Account studentAccount = accountService.getAccountByUsername(username);
//        if (studentAccount != null) {
//            AccountResponseDto accountDto = accountMapper.mapperAccount(studentAccount);
//            return new ResponseEntity<>(accountDto, HttpStatus.OK);
//        }
//        return new ResponseEntity<>("Student not found.", HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @PostMapping("/save")
//    public ResponseEntity<?> saveStudent(@RequestBody AccountRequestDto accountDto) {
//        Account account = accountMapper.mapperRequestDto(accountDto);
//        try {
//            accountService.save(account);
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//}
