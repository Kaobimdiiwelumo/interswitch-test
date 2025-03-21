package com.example.INTERSWITCH.CONTROLLER;

import com.example.INTERSWITCH.DTO.UserDTO;
import com.example.INTERSWITCH.ENTITY.User;
import com.example.INTERSWITCH.REPOSITORY.UserRepository;
import com.example.INTERSWITCH.SERVICE.UserService;
import jakarta.servlet.http.HttpSession;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Operation(summary = "Register a new user", description = "Creates a new user with a username and email.")
    @PostMapping("/create")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }
    @Operation(summary = "Login a user", description = "Logs in a user using the username and starts a session.")
    @PostMapping("/login")
    public String login(@RequestParam String username, HttpSession session) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            session.setAttribute("userId", user.get().getId());
            return "User " + username + " logged in successfully.";
        } else {
            return "User not found. Please check your username.";
        }
    }
    @Operation(summary = "Logout a user", description = "Logs out the current user and ends the session.")
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "User logged out successfully.";
    }
}
