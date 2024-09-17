package com.example.wsa.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    // @Autowired was working so remove constructor and uncomment this if problems.
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }
    // Remove PasswordEncoder-related code since security is not required
    // @Autowired
    // private PasswordEncoder passwordEncoder;
    // /api/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser) {
        User user = userService.findByUsername(loginUser.getUsername());
        // Directly compare the plain text password (not secure but as per requirement)
        if (user != null && loginUser.getPassword().equals(user.getPassword())) {
            // Create a response map to include the userId
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("userId", user.getId());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
