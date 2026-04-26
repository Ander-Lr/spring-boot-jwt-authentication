package com.example.JWT_SpringBoot.auth.service;

import com.example.JWT_SpringBoot.auth.controller.LoginRequest;
import com.example.JWT_SpringBoot.auth.controller.RegisterRequest;
import com.example.JWT_SpringBoot.auth.controller.TokenResponse;
import com.example.JWT_SpringBoot.auth.repository.Token;
import com.example.JWT_SpringBoot.auth.repository.TokenRepository;
import com.example.JWT_SpringBoot.user.User;
import com.example.JWT_SpringBoot.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;


    public TokenResponse register(RegisterRequest request){
        var user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();
         var savedUser = userRepository.save(user);
         var jwtToken = jwtService.generateToken(user);
         var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser,jwtToken);
        return new TokenResponse(jwtToken,refreshToken);
    }

    public TokenResponse login(LoginRequest request){
        return null;
    }

    public void saveUserToken(User user, String jwtToken){
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(Token.TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    public TokenResponse refreshToken(String authHeader) {
        return null;
    }
}
