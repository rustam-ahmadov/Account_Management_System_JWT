package com.example.acc_mgmt_sys.service;

import com.example.acc_mgmt_sys.entity.User;
import com.example.acc_mgmt_sys.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return ((List<User>) userRepository.findAll());
    }
}
