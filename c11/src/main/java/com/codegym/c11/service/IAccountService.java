package com.codegym.c11.service;

import com.codegym.c11.model.dto.request.AccountRequestDto;
import com.codegym.c11.model.entity.Account;
import org.springframework.security.core.userdetails.UserDetails;

public interface IAccountService {
    UserDetails loadUserByUsername(String username);

    void save(Account account);

    String login(AccountRequestDto accountDto);

    void saveNewAccount(Account newAccount);

    boolean validateAccount(Account newAccount);
}
