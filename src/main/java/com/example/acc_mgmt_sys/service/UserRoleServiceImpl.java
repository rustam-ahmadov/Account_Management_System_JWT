package com.example.acc_mgmt_sys.service;

import com.example.acc_mgmt_sys.entity.UsersRoles;
import com.example.acc_mgmt_sys.repo.UserRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void addRoleToUser(UsersRoles usersRoles) {
        userRoleRepository.save(usersRoles);
    }
}
