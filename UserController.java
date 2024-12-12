package com.coforge.finance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.coforge.finance.model.User;
import com.coforge.finance.service.UserService;

import java.util.List;
import java.util.Optional;
 
@RestController
@RequestMapping("/users")
public class UserController {
 
    @Autowired
    private UserService userService;
 
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }
 
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.findUserByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
 
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.findUserByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
 
    @GetMapping("/active")
    public ResponseEntity<List<User>> getActiveUsers() {
        List<User> activeUsers = userService.findActiveUsers();
        return ResponseEntity.ok(activeUsers);
    }
 
    @PutMapping("/deactivate/{userId}")
    public ResponseEntity<User> deactivateUser(@PathVariable Long userId) {
        User user = userService.deactivateUser(userId);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }
 
    @PutMapping("/activate/{userId}")
    public ResponseEntity<User> activateUser(@PathVariable Long userId) {
        User user = userService.activateUser(userId);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }
 
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
 
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}