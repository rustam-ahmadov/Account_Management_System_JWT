package com.example.acc_mgmt_sys.controller;


import com.example.acc_mgmt_sys.entity.Role;
import com.example.acc_mgmt_sys.repo.RoleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
public class RoleController {

    private final RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostMapping(path = "add/role")
    public ResponseEntity<?> addRole(@RequestBody Role newRole) {
        String response = "Role has been successfully added";
        try {
            Role role = roleRepository.save(newRole);
        } catch (Exception e) {
            response = "Something went wrong while operation with DB";
        }
        return ResponseEntity.ok(response);
    }
}
