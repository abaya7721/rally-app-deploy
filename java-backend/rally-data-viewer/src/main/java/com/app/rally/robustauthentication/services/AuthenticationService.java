package com.app.rally.robustauthentication.services;

import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.rally.robustauthentication.data.model.Role;
import com.app.rally.robustauthentication.data.model.User;
import com.app.rally.robustauthentication.data.repository.UserRepository;
import com.app.rally.robustauthentication.dto.JwtAuthenticationResponse;
import com.app.rally.robustauthentication.dto.SignInRequest;


@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, UserService userService, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    public JwtAuthenticationResponse signup(SignInRequest request) {
        User user = new User(UUID.randomUUID(), request.getUsername(), passwordEncoder.encode(request.getPassword()), true,  Role.ROLE_USER);
        user = userService.save(user);
        String jwt = jwtService.generateToken(user);

        return  new JwtAuthenticationResponse(jwt);
    }


    public JwtAuthenticationResponse signin(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername());
        String jwt = jwtService.generateToken(user);

        return  new JwtAuthenticationResponse(jwt);
    }

}