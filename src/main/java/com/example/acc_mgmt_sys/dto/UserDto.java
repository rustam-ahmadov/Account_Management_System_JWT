package com.example.acc_mgmt_sys.dto;

import com.example.acc_mgmt_sys.entity.Account;
import com.example.acc_mgmt_sys.entity.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class UserDto {
    private String username;
    private String password;
    private String lastname;
    private String name;
    private String email;
    private String address;
    private String phone;
    private LocalDate dob;
    private Collection<Long> roles = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();

    //region Constructor , Getters and Setters
    public UserDto() {
    }

    public UserDto(String username, String password, String lastname, String name, String email, String address, String phone, LocalDate dob) {

        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.dob = dob;
    }


    public Collection<Long> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Long> roles) {
        this.roles = roles;
    }

    public void setAccount(Account account) {
        accounts.add(account);
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Optional<Account> getAccountbyNumber(String number) {
        return accounts.stream().filter(account -> account.getNumber() == number).findFirst();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    //endregion

}
