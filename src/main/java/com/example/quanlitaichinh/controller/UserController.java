package com.example.quanlitaichinh.controller;

import com.example.quanlitaichinh.dto.request.ChangePasswordRequest;
import com.example.quanlitaichinh.model.User;
import com.example.quanlitaichinh.repo.UserRepository;
import com.example.quanlitaichinh.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin("*")

public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        return new ResponseEntity<>((List<User>) userService.findAll(), HttpStatus.OK);
    }
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@Valid @RequestBody ChangePasswordRequest request) {
        User user = userService.getCurrentUser();
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword()))
            return ResponseEntity.status(HttpStatus.OK).body("Invalid current password");

        if (passwordEncoder.matches(request.getNewPassword(), user.getPassword()))
            return ResponseEntity.status(HttpStatus.OK).body("New password must be different from the current password");

        String newPasswordEncoded = passwordEncoder.encode(request.getNewPassword());
        user.setPassword(newPasswordEncoded);
        userRepository.save(user);

        return ResponseEntity.ok("Password changed successfully");
    }
    @PutMapping("/current")
    public ResponseEntity<User> updateCurrentUser(@RequestBody User updatedUser) {
        User currentUser = userService.updateUser(updatedUser);
        return ResponseEntity.ok(currentUser);
    }

}
