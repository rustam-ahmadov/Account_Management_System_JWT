package com.example.acc_mgmt_sys.repo;

import com.example.acc_mgmt_sys.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findByNumber(String number);
    List<Account> findAccountByUser_Username(String username);

    Account findAccountByNumber(String number);

    void deleteAccountByNumber(String number);
}
