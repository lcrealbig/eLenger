package com.api.lenger.common.security.auth;

import com.api.lenger.domain.user.User;
import com.api.lenger.domain.user.UserDto;
import com.api.lenger.domain.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.springframework.security.core.userdetails.User.builder;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User appUser = userRepository.findByIdentityEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
        return builder()
                .username(appUser.getIdentity().getEmail())
                .password(appUser.getIdentity().getPasswordHash())
                .build();

    }


}