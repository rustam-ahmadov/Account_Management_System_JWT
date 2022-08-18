package com.example.acc_mgmt_sys.repo;

import com.example.acc_mgmt_sys.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}
