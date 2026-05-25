package com.api.lenger.domain.auth;

import com.api.lenger.common.login.LoginRequest;
import com.api.lenger.common.register.RegisterRequest;
import com.api.lenger.domain.identity.EmailService;
import com.api.lenger.domain.user.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final UserMapper userMapper;

    public UserDto register(RegisterRequest requestBody) {
        if (userRepository.findByIdentityEmail(requestBody.email()).isPresent()) {

            log.warn("Registration attempt for request ");
            throw new RegisterException("Email already in use");
        }

        var user = new User();
        user.getIdentity().setEmail(requestBody.email());
        user.getIdentity().setPasswordHash(passwordEncoder.encode(requestBody.password()));
        user.setEnabled(false);

        var token = UUID.randomUUID().toString();
        user.setConfirmationToken(token);
        user.setConfirmationTokenExpiration(LocalDateTime.now().plusHours(24));

        userRepository.save(user);

        emailService.sendConfirmationEmail(user.getIdentity().getEmail(), token);

        return userMapper.toDto(user);
    }

    public UserDto login(LoginRequest request) {
        var user = userRepository.findByIdentityEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(request.password() , user.getIdentity().getPasswordHash())) {
            throw new RuntimeException("Invalid password");
        }

        if (!user.isEnabled()) {
            throw new RuntimeException("Account not activated");
        }

        return userMapper.toDto(user);
    }

    public UserDto confirmEmail(String token) {
        var user = userRepository.findByConfirmationToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        if (user.getConfirmationTokenExpiration().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expired");
        }

        user.setEnabled(true);
        user.setConfirmationToken(null);
        user.setConfirmationTokenExpiration(null);
        userRepository.save(user);

        return userMapper.toDto(user);
    }
}