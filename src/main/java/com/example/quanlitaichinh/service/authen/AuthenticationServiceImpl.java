package com.example.quanlitaichinh.service.authen;

import com.example.quanlitaichinh.dto.request.SignInRequest;
import com.example.quanlitaichinh.dto.request.SignUpRequest;
import com.example.quanlitaichinh.dto.response.ApiResponse;
import com.example.quanlitaichinh.dto.response.JwtAuthenticationResponse;
import com.example.quanlitaichinh.model.User;
import com.example.quanlitaichinh.repo.UserRepository;
import com.example.quanlitaichinh.service.JwtService;
import com.example.quanlitaichinh.service.user.AuthenticationService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public ApiResponse signup(SignUpRequest request) {
        String username = request.getUsername();
        String phoneNumber = request.getPhoneNumber();
        String email = request.getEmail();

        if (userRepository.existsByUsername(username)) {
            return new ApiResponse("Username already exists.", "409", null);
        }

        if (userRepository.existsByEmail(email)) {
            return new ApiResponse("Email already exists.", "409", null);
        }

        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            return new ApiResponse("Phone number already exists.", "409", null);
        }

        String password = request.getPassword();
        if (StringUtils.isEmpty(password)) {
            return new ApiResponse("Password is required.", "400", null);
        }

        String encodedPassword = passwordEncoder.encode(password);

        User user = User.builder()
                .phoneNumber(phoneNumber)
                .email(email)
                .username(username)
                .password(encodedPassword)
                .build();

        userRepository.save(user);

        return new ApiResponse("Signup successfully", "201", user);
    }


    @Override
    public JwtAuthenticationResponse signin(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password."));
        if (user.isBlocked()) {
            return null;
        }
        return jwtService.responseJWT(user);
    }
}
