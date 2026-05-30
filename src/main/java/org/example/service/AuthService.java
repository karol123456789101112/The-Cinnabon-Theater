package org.example.service;

import org.example.domain.AppUser;
import org.example.exception.InactiveUserException;
import org.example.repository.AppUserRepository;
import org.example.security.JwtUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    AuthService(AppUserRepository appUserRepository,
                JwtUtil jwtUtil){
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.jwtUtil = jwtUtil;
    }

    public String login(String email, String password) {

        AppUser user = appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new BadCredentialsException("badCredentials"));

        if (!user.isEnabled()) {
            throw new InactiveUserException("User is not active");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("badCredentials");
        }

        return jwtUtil.generateToken(user);
    }
}
