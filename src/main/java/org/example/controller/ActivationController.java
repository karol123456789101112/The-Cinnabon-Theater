package org.example.controller;

import org.example.domain.AppUser;
import org.example.domain.UserStatus;
import org.example.domain.VerificationToken;
import org.example.repository.AppUserRepository;
import org.example.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class ActivationController {

    @Autowired
    private VerificationTokenRepository tokenRepo;

    @Autowired
    private AppUserRepository userRepo;

    @RequestMapping("/activate")
    public String activate(@RequestParam("token") String token) {

        VerificationToken vt = tokenRepo.findByToken(token);

        if (vt == null || vt.getExpiryDate().isBefore(LocalDateTime.now())) {
            return "invalidToken";
        }

        AppUser user = vt.getUser();
        user.setStatus(UserStatus.ACTIVE);

        userRepo.save(user);
        tokenRepo.delete(vt);

        return "activationSuccess";
    }
}