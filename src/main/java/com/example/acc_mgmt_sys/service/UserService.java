package com.example.acc_mgmt_sys.service;

import com.example.acc_mgmt_sys.entity.Account;
import com.example.acc_mgmt_sys.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {
    User addUser(User user);
    User getUser(String username);

    List<User> getAllUsers();

    List<Account> getUserAccount(Long userId);
    void addUserAccount(Long userId,Account account);
}
