package com.example.acc_mgmt_sys.repo;

import com.example.acc_mgmt_sys.entity.UsersRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UsersRoles,Long> {

}
