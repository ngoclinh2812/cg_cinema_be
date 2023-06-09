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

    public AccountResponseDto mapperFromEntityToDto(Account account){
        AccountResponseDto accountDto = new AccountResponseDto();
        BeanUtils.copyProperties(account, accountDto);
        return accountDto;
    }

    public Account mapperResponseDto(AccountResponseDto accountDto) {
        Account accountEntity = new Account();
        BeanUtils.copyProperties(accountDto, accountEntity);
        return accountEntity;
    }

    public Account mapperFromRequestDtoToEntity(AccountRequestDto accountDto) {
        Account accountEntity = new Account();
        BeanUtils.copyProperties(accountDto, accountEntity);
        return accountEntity;
    }

}
