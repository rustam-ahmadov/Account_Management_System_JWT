package com.example.acc_mgmt_sys.service;


import com.example.acc_mgmt_sys.entity.UsersRoles;
import org.springframework.stereotype.Service;

@Service
public interface UserRoleService {
    void addRoleToUser(UsersRoles usersRoles);
}
