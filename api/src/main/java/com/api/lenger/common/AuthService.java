package com.api.lenger.common;

import com.api.lenger.domain.identity.EmailService;
import com.api.lenger.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService; // Mockowany na razie

    public UserDto register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(false); // Na początek nieaktywny

        // Generuj token potwierdzenia (mockowany)
        String token = UUID.randomUUID().toString();
        user.setConfirmationToken(token);
        user.setTokenExpiration(LocalDateTime.now().plusHours(24));

        userRepository.save(user);

        // Tutaj wyślij email z linkiem potwierdzenia (mockowany)
        // emailService.sendConfirmationEmail(user.getEmail(), token);

        return mapToUserDto(user);
    }

    public UserDto login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        if (!user.isEnabled()) {
            throw new RuntimeException("Account not activated");
        }

        return mapToUserDto(user);
    }

    public UserDto confirmEmail(String token) {
        User user = userRepository.findByConfirmationToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        if (user.getTokenExpiration().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expired");
        }

        user.setEnabled(true);
        user.setConfirmationToken(null);
        user.setTokenExpiration(null);
        userRepository.save(user);

        return mapToUserDto(user);
    }

    private UserDto mapToUserDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setEnabled(user.isEnabled());
        return dto;
    }
}