package com.example.proxysellertesttask.service;

import com.example.proxysellertesttask.config.security.JwtService;
import com.example.proxysellertesttask.entity.auth.AuthenticationRequest;
import com.example.proxysellertesttask.entity.auth.AuthenticationResponse;
import com.example.proxysellertesttask.entity.auth.RegisterRequest;
import com.example.proxysellertesttask.entity.token.Token;
import com.example.proxysellertesttask.entity.token.TokenType;
import com.example.proxysellertesttask.entity.user.Role;
import com.example.proxysellertesttask.entity.user.User;
import com.example.proxysellertesttask.exception.UserExistsException;
import com.example.proxysellertesttask.repository.TokenRepository;
import com.example.proxysellertesttask.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    private SequenceGenerator sequenceGenerator;

    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new UserExistsException("User already exists!");
        }

        var user = User.builder()
                .id(sequenceGenerator.generateSequence(User.SEQUENCE_NAME))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .joinDate(LocalDate.now())
                .role(Role.USER)
                .build();

        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser.getId(), jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user.getId(), jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(Long userId, String jwtToken) {
        var token = Token.builder()
                .id(sequenceGenerator.generateSequence(Token.SEQUENCE_NAME))
                .userId(userId)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findAllByUserIdAndExpiredAndRevoked(user.getId(), false, false);
        if (validUserTokens.isEmpty()) {
            return;
        }
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}