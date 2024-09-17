package com.example.wsa.login;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findByUsername(String username);
}
