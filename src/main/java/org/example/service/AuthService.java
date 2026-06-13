package org.example.service;

import org.example.domain.AppUser;
import org.example.exception.InactiveUserException;
import org.example.repository.AppUserRepository;
import org.example.security.JwtUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static org.example.domain.UserStatus.DELETED;
import static org.example.domain.UserStatus.INACTIVE;

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

        if (user.getStatus() == INACTIVE) {
            throw new InactiveUserException("User is not active");
        }
        else if (user.getStatus() == DELETED) {
            throw new InactiveUserException("User is deleted");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("badCredentials");
        }

        return jwtUtil.generateToken(user);
    }
}
