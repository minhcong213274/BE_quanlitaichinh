package com.example.quanlitaichinh.service;

import com.example.quanlitaichinh.dto.response.JwtAuthenticationResponse;
import com.example.quanlitaichinh.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUserName(String token);

    Long extractExpirationTime(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

    JwtAuthenticationResponse responseJWT(User user);
}
