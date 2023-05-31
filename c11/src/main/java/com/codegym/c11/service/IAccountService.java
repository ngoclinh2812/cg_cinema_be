package com.studentmanagement.linhoang.service;

import com.studentmanagement.linhoang.model.dto.request.AccountRequestDto;
import com.studentmanagement.linhoang.model.dto.response.AccountResponseDto;
import com.studentmanagement.linhoang.model.entity.Account;
import org.springframework.security.core.userdetails.UserDetails;

public interface IAccountService {
    UserDetails loadUserByUsername(String username);

    void save(Account account);

    String login(AccountRequestDto accountDto);
}
