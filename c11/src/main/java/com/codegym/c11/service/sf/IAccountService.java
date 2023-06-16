package com.codegym.c11.service.sf;

import com.codegym.c11.model.dto.request.AccountRequestDto;
import com.codegym.c11.model.entity.Account;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface IAccountService {
    UserDetails loadUserByUsername(String username);

    void save(Account account);

    String login(AccountRequestDto accountDto);

    void saveNewAccount(Account newAccount);

    boolean validateAccount(Account newAccount);

    String loginAsAdmin(AccountRequestDto accountDto);

    Account findByUsername(String username);

    Account findById(Long id);
}
