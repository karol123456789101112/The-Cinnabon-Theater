package org.example.service;

import jakarta.transaction.Transactional;
import org.example.domain.AppUser;
import org.example.domain.VerificationToken;
import org.example.repository.AppUserRepository;
import org.example.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CleanupService {

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Scheduled(fixedRate = 3600000) // 1 hour
    @Transactional
    public void deleteUnactivatedUsers() {

        LocalDateTime now = LocalDateTime.now();

        List<VerificationToken> expiredTokens =
                tokenRepository.findAll().stream()
                        .filter(t -> t.getExpiryDate().isBefore(now))
                        .toList();

        for (VerificationToken token : expiredTokens) {

            AppUser user = token.getUser();

            if (!user.isEnabled()) {
                userRepository.delete(user);
            }

            tokenRepository.delete(token);
        }
    }
}