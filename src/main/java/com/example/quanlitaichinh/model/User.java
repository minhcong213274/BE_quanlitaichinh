package com.example.quanlitaichinh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    private String fullName;
    private String address;
    private String birthday;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String phoneNumber;
    private boolean isBlocked;
}
