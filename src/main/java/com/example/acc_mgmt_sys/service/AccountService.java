package com.example.acc_mgmt_sys.service;

import com.example.acc_mgmt_sys.entity.Account;
import com.example.acc_mgmt_sys.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    void addAccount(Account account);
    List<Account> getAccountsByUserName(String username);

    Account getAccountByNumber(String number);
    void deleteAccountByNumber(String number);

}
