package com.moa.synthizerbackend.auth.service;

import com.moa.synthizerbackend.auth.dto.AuthResponse;
import com.moa.synthizerbackend.auth.dto.LoginRequest;
import com.moa.synthizerbackend.auth.dto.RegisterRequest;
import com.moa.synthizerbackend.auth.jwt.JwtService;
import com.moa.synthizerbackend.common.exception.ApplicationException;
import com.moa.synthizerbackend.user.entity.User;
import com.moa.synthizerbackend.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new ApplicationException("User already exists with email: " + request.email());
        }

        User user = new User();
        user.setEmail(request.email());
        user.setFullName(request.fullName());
        user.setPasswordHash(passwordEncoder.encode(request.password()));

        User savedUser = userRepository.save(user);
        String token = jwtService.generateToken(savedUser);

        return new AuthResponse(token, null, "Bearer");
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new ApplicationException("User not found with email: " + request.email()));

        String token = jwtService.generateToken(user);

        return new AuthResponse(token, null, "Bearer");
    }
}
