package com.example.acc_mgmt_sys.service;

import com.example.acc_mgmt_sys.entity.Account;
import com.example.acc_mgmt_sys.entity.User;
import com.example.acc_mgmt_sys.repo.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void addAccount(Account account) {

        accountRepository.save(account);
    }

    @Override
    public List<Account> getAccountsByUserName(String username) {
        return accountRepository.findAccountByUser_Username(username);
    }

    @Override
    public Account getAccountByNumber(String number) {
        return accountRepository.findByNumber(number);
    }

    @Override
    @Transactional
    public void deleteAccountByNumber(String number) {
        accountRepository.deleteAccountByNumber(number);
    }
}
