package com.example.acc_mgmt_sys.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String number;
    private BigDecimal balance;

    @ManyToOne()
    private User user;

    private LocalDate created;
    private LocalDate deleted;

    public Account() {

    }

    public Account(String number, BigDecimal balance) {
        this.number = number;
        this.balance = balance;
        this.created = LocalDate.now();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
