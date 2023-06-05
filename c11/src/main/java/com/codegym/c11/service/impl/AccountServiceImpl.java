package com.codegym.c11.service.impl;

import com.codegym.c11.model.dto.request.AccountRequestDto;
import com.codegym.c11.model.entity.Account;
import com.codegym.c11.model.entity.UserPrinciple;
import com.codegym.c11.repository.AccountRepository;
import com.codegym.c11.security.JwtProvider;
import com.codegym.c11.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account  = accountRepository.findByUsername(username);
        if (!account.isPresent()){
            throw new UsernameNotFoundException(username);
        }

        return UserPrinciple.build(account.get());
    }

    @Override
    public void save(Account account) {

        // Check if the account already exists
        Optional<Account> optionalExistingAccount = accountRepository.findById(account.getId());

        if (optionalExistingAccount.isPresent()) {
            // If the account exists, update the properties
            Account existingAccount = optionalExistingAccount.get();

            // Only update the non-null properties of the existing account
            if (account.getFirstName() != null) {
                existingAccount.setFirstName(account.getFirstName());
            }
            if (account.getLastName() != null) {
                existingAccount.setLastName(account.getLastName());
            }
            if (account.getPhone() != null) {
                existingAccount.setPhone(account.getPhone());
            }
            if (account.getEmail() != null) {
                existingAccount.setEmail(account.getEmail());
            }
            if (account.getUsername() != null) {
                existingAccount.setUsername(account.getUsername());
            }
            if (account.getPassword() != null) {
                // Hash the password using BCryptPasswordEncoder
                String hashedPassword = new BCryptPasswordEncoder(10).encode(account.getPassword());
                existingAccount.setPassword(hashedPassword);
            }

            accountRepository.save(existingAccount);
        } else {
            // If the account doesn't exist, save the new account
            String hashedPassword = new BCryptPasswordEncoder(10).encode(account.getPassword());
            account.setPassword(hashedPassword);
            accountRepository.save(account);
        }
    }


        public void mergeAccounts(Account existingAccount, Account updatedAccount) {
        if (updatedAccount.getUsername() != null) {
            existingAccount.setUsername(updatedAccount.getUsername());
        }
        if (updatedAccount.getPassword() != null) {
            existingAccount.setPassword(updatedAccount.getPassword());
        }
        // Update other properties as needed
    }


    @Override
    public String login(AccountRequestDto accountDto) {
        Account account = getAccountByUsername(accountDto.getUsername());
        if (account != null && checkLogin(accountDto)) {
            String jwt = jwtProvider.generateTokenLogin(account);
            return jwt;
        }
        return null;
    }

    @Override
    public void saveNewAccount(Account newAccount) {
        String hashedPassword = BCrypt.hashpw(newAccount.getPassword(), BCrypt.gensalt(10));
        newAccount.setPassword(hashedPassword);
        accountRepository.save(newAccount);
    }

    @Override
    public boolean validateAccount(Account newAccount) {
        boolean isUsername = validateUsername(newAccount.getUsername());
        boolean isEmail = validateEmail(newAccount.getEmail());
        boolean isPhone = validatePhone(newAccount.getPhone());

        if (isUsername && isEmail && isPhone) {
            return true;
        }
        return false;
    }

    private boolean validatePhone(String phone) {
        Optional<Account> acc = accountRepository.findByPhoneNumber(phone);
        if (acc.isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean validateEmail(String email) {
        Optional<Account> acc = accountRepository.findByEmail(email);
        if (acc.isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean validateUsername(String username) {
        Optional<Account> acc = accountRepository.findByUsername(username);
        if (acc.isEmpty()) {
            return true;
        }
        return false;
    }

    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username).orElse(null);
    }

    public boolean checkLogin(AccountRequestDto account) {
        Account searchAccount = getAccountByUsername(account.getUsername());
        if (searchAccount != null) {
            String password = account.getPassword();
            String passwordDb = searchAccount.getPassword();
            return BCrypt.checkpw(password, passwordDb);
        }
        return false;
    }


}
