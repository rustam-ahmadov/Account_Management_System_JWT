package com.example.acc_mgmt_sys.repo;

import com.example.acc_mgmt_sys.entity.Account;
import com.example.acc_mgmt_sys.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
