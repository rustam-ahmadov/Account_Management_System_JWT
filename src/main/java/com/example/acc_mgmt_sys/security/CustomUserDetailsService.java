package com.example.acc_mgmt_sys.security;

import com.example.acc_mgmt_sys.entity.Role;
import com.example.acc_mgmt_sys.security.JwtTokenProvider;
import com.example.acc_mgmt_sys.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public CustomUserDetailsService(JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {

        String username = jwtTokenProvider.getUsernameFromJWT(token);
        com.example.acc_mgmt_sys.entity.User user = userService.getUser(username);


        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(userRole -> {
            authorities.add(new SimpleGrantedAuthority(userRole.getName()));
        });
        return new User(username, user.getPassword(), authorities);
    }
}
