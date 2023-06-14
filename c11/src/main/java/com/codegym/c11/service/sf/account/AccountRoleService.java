package com.codegym.c11.service.sf.account;

import com.codegym.c11.enums.ERole;
import com.codegym.c11.model.entity.Account;
import com.codegym.c11.model.entity.AccountRoles;
import com.codegym.c11.repository.AccountRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountRoleService {

    @Autowired
    private AccountRoleRepository accountRoleRepository;

    public void saveAccountRoleAsUser(Account account) {
        List<AccountRoles> accountRoles = account.getRolesList();
        accountRoles.add(new AccountRoles(account, ERole.ROLE_USER));
        for (AccountRoles role : accountRoles) {
            accountRoleRepository.save(role);
        }
    }
}
