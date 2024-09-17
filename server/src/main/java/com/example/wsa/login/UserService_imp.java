package com.example.wsa.login;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService_imp implements UserService {

    private final UserRepository userRepository;

    public UserService_imp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
