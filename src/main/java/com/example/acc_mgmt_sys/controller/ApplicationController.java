package com.example.acc_mgmt_sys.controller;

import com.example.acc_mgmt_sys.dto.AccountDto;
import com.example.acc_mgmt_sys.dto.UserDto;
import com.example.acc_mgmt_sys.entity.Account;
import com.example.acc_mgmt_sys.entity.Role;
import com.example.acc_mgmt_sys.entity.User;
import com.example.acc_mgmt_sys.entity.UsersRoles;
import com.example.acc_mgmt_sys.service.AccountService;
import com.example.acc_mgmt_sys.service.UserRoleService;
import com.example.acc_mgmt_sys.service.UserService;
import org.hibernate.annotations.NotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api")
public class ApplicationController {

    private final UserService userService;
    private final UserRoleService userRoleService;
    private final AccountService accountService;

    public ApplicationController(UserService userService, AccountService accountService, UserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.accountService = accountService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping(path = "{username}/accounts")
    public ResponseEntity<?> getUserAccounts(@PathVariable String username) {

        User user  = userService.getUser(username);

        if (user != null)
            return ResponseEntity.ok(user.getAccounts());

        return ResponseEntity.ok("There is no such user in DB");
    }

    @GetMapping(path = "account/{accountNumber}")
    public ResponseEntity<?> getUserAccount(@PathVariable String accountNumber) {

        Account accounts = accountService.getAccountByNumber(accountNumber);

        if (accounts != null)
            return ResponseEntity.ok(accounts);

        return ResponseEntity.ok("Such account has not been founded");
    }

    @DeleteMapping(path = "/delete/{accountNumber}")
    public ResponseEntity<?> deleteUserAccount(@PathVariable String accountNumber) {

        Account account = accountService.getAccountByNumber(accountNumber);
        String response = "Was not founded";

        if (account != null) {
            accountService.deleteAccountByNumber(accountNumber);
            response = "Account:" + accountNumber + " was successfully deleted";
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/add/account/")
    public ResponseEntity<?> addUserAccount(@RequestBody AccountDto accountDto) {
        Account account = accountService.getAccountByNumber(accountDto.getNumber());
        User user = userService.getUser(accountDto.getUsername());

        if (account != null) {
            return ResponseEntity.ok("There is already account with same number in db");
        }

        if(user ==null){
            return ResponseEntity.ok("There is no user with such name in DB");
        }

        account = new Account(accountDto.getNumber(), accountDto.getBalance(),user);
        accountService.addAccount(account);

        return ResponseEntity.ok("Account has been successfully added to user " + user.getUsername());
    }

    @PostMapping(path = "user/add")
    public ResponseEntity<?> addUser(@RequestBody UserDto newUser) {

        User user = userService.getUser(newUser.getUsername());

        if (user != null) {
            return ResponseEntity.ok("There is already such user in DB");
        }

        user = new User();
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());
        Long id = userService.addUser(user).getId();

        for (Long role : newUser.getRoles()) {
            UsersRoles usersRole = new UsersRoles();
            usersRole.setUser_id(user.getId());
            usersRole.setRoles_id(role);
            userRoleService.addRoleToUser(usersRole);
        }

        return ResponseEntity.ok("User was added successfully");
    }

}
