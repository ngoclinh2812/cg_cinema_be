package com.studentmanagement.linhoang.utils;

import com.studentmanagement.linhoang.model.dto.request.AccountRequestDto;
import com.studentmanagement.linhoang.model.dto.response.AccountResponseDto;
import com.studentmanagement.linhoang.model.entity.Account;
import com.studentmanagement.linhoang.repository.AccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountMapper {

    @Autowired
    private AccountRepository accountRepository;

    public AccountResponseDto mapperAccount(Account account){
        AccountResponseDto accountDto = new AccountResponseDto();
        BeanUtils.copyProperties(account, accountDto);
        return accountDto;
    }

    public Account mapperResponseDto(AccountResponseDto accountDto) {
        Account accountEntity = new Account();
        BeanUtils.copyProperties(accountDto, accountEntity);
        return accountEntity;
    }

    public Account mapperRequestDto(AccountRequestDto accountDto) {
        Account accountEntity = new Account();
        BeanUtils.copyProperties(accountDto, accountEntity);
        return accountEntity;
    }
}
