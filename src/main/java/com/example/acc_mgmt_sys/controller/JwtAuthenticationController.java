package com.example.acc_mgmt_sys.controller;


import com.example.acc_mgmt_sys.dto.JwtRequest;
import com.example.acc_mgmt_sys.dto.JwtResponse;
import com.example.acc_mgmt_sys.entity.User;
import com.example.acc_mgmt_sys.security.CustomUserDetailsService;
import com.example.acc_mgmt_sys.security.JwtTokenProvider;
import com.example.acc_mgmt_sys.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin()
public class JwtAuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final CustomUserDetailsService customUserDetailsService;

    public JwtAuthenticationController(UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, CustomUserDetailsService customUserDetailsService) {
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


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
        String response = "User has been added in DB successfully";
        try {
            userService.addUser(user);
        } catch (Exception e) {
            response = "There is already such user in DB";
        }
        return ResponseEntity.ok().body(response);
    }

}

