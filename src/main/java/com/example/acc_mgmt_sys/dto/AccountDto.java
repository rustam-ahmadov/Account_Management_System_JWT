package com.example.acc_mgmt_sys.dto;

import com.example.acc_mgmt_sys.entity.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AccountDto {
    private String number;
    private BigDecimal balance;
    private String username;

    public AccountDto() {

    }

    public AccountDto(String number, BigDecimal balance,String username) {
        this.number = number;
        this.balance = balance;
        this.username = username;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    public String getUsername(){return this.username; }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}

