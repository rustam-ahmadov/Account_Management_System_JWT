package com.example.acc_mgmt_sys;

import com.example.acc_mgmt_sys.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountManagementSystemApplication.class, args);
    }

    CommandLineRunner run(UserService userService) {
        return args -> {

        };
    }
}
