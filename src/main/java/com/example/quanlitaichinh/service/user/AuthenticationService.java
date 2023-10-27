package com.example.quanlitaichinh.service.user;

import com.example.quanlitaichinh.dto.request.SignInRequest;
import com.example.quanlitaichinh.dto.request.SignUpRequest;
import com.example.quanlitaichinh.dto.response.ApiResponse;
import com.example.quanlitaichinh.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    ApiResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);
}
