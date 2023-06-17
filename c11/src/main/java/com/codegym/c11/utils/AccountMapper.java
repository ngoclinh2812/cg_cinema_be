package com.codegym.c11.utils;

import com.codegym.c11.model.dto.request.AccountRequestDto;
import com.codegym.c11.model.dto.response.AccountResponseDto;
import com.codegym.c11.model.entity.Account;
import com.codegym.c11.repository.AccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    @Autowired
    private AccountRepository accountRepository;


    public Account convertFromRequestDtoToEntity(AccountRequestDto accountDto) {
        Account accountEntity = new Account();
        BeanUtils.copyProperties(accountDto, accountEntity);
        return accountEntity;
    }

    public AccountResponseDto convertFromEntityToDto(Account account) {
        AccountResponseDto accountResponseDto = new AccountResponseDto();
        BeanUtils.copyProperties(account, accountResponseDto);
        return accountResponseDto;
    }
}
