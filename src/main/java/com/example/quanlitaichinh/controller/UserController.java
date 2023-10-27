package com.example.quanlitaichinh.controller;

import com.example.quanlitaichinh.model.User;
import com.example.quanlitaichinh.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin("*")

public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        return new ResponseEntity<>((List<User>) userService.findAll(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User isSuccessfullyCreated = userService.save(user);
        if (isSuccessfullyCreated != null) return new ResponseEntity<>(isSuccessfullyCreated, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
//    @PutMapping("/changePassword")
//    public ResponseEntity<User> changePassword(@RequestBody ChangePasswordRequest request){
//    }
}
