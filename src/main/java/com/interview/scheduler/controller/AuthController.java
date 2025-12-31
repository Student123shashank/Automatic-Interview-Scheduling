package com.interview.scheduler.controller;

import com.interview.scheduler.entity.User;
import com.interview.scheduler.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        userRepository.save(user);
        return "Signup successful";
    }

    
   @PostMapping("/login")
public String login(@RequestBody User user, HttpSession session) {

    User dbUser = userRepository.findByEmail(user.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (!dbUser.getPassword().equals(user.getPassword())) {
        throw new RuntimeException("Invalid credentials");
    }

    session.setAttribute("LOGGED_IN_USER", dbUser);
    return "Login successful";
}


    
    @PostMapping("/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }
}
