package com.example.wsa.login;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findByUsername(String username);
}
