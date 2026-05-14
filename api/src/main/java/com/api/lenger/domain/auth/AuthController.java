package com.api.lenger.domain.auth;

import com.api.lenger.common.login.LoginRequest;
import com.api.lenger.common.register.RegisterRequest;
import com.api.lenger.domain.user.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid RegisterRequest body) {
        return ResponseEntity.ok(authService.register(body));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}