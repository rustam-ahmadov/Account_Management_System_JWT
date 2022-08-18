package com.example.acc_mgmt_sys.controller;


import com.example.acc_mgmt_sys.dto.JwtRequest;
import com.example.acc_mgmt_sys.dto.UserDto;
import com.example.acc_mgmt_sys.entity.User;
import com.example.acc_mgmt_sys.entity.UsersRoles;
import com.example.acc_mgmt_sys.security.CustomUserDetailsService;
import com.example.acc_mgmt_sys.security.JwtTokenProvider;
import com.example.acc_mgmt_sys.service.UserRoleService;
import com.example.acc_mgmt_sys.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
public class JwtAuthenticationController {
    private final UserRoleService userRoleService;
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final CustomUserDetailsService customUserDetailsService;

    public JwtAuthenticationController(UserRoleService userRoleService, UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, CustomUserDetailsService customUserDetailsService) {
        this.userRoleService = userRoleService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = tokenProvider;
        this.customUserDetailsService = customUserDetailsService;
        this.userService = userService;
    }


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        User user = userService.getUser(authenticationRequest.getUsername());
        if (user == null)
            return ResponseEntity.ok("This user has not been registered");

        String token = jwtTokenProvider.generateToken(user);
        return ResponseEntity.ok().body(token);
    }


    @PostMapping(value = "/register")
    public ResponseEntity<?> saveUser(@RequestBody UserDto newUser) throws Exception {

        User user = userService.getUser(newUser.getUsername());

        if (user != null) {
            return ResponseEntity.ok("There is already such user in DB");
        }

        user = new User();
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());
        Long id = userService.addUser(user).getId();

        for (Long role : newUser.getRoles()) {
            UsersRoles usersRole = new UsersRoles();
            usersRole.setUser_id(user.getId());
            usersRole.setRoles_id(role);
            userRoleService.addRoleToUser(usersRole);
        }

        return ResponseEntity.ok("User was added successfully");
    }

}

