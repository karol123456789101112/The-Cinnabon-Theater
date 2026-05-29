package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.Dto.AppUserDto;
import org.example.service.AppUserService;
import org.example.service.ReCaptchaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AppUserController {

    private static final Logger log = LoggerFactory.getLogger(AppUserController.class);

    private final AppUserService appUserService;
    private final ReCaptchaService reCaptchaService;

    public AppUserController(AppUserService appUserService,
                             ReCaptchaService reCaptchaService) {
        this.appUserService = appUserService;
        this.reCaptchaService = reCaptchaService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AppUserDto dto) {

        System.out.println("COS");

        if (!reCaptchaService.verify(dto.getRecaptchaToken())) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Invalid reCAPTCHA");
        }

        appUserService.addAppUser(dto);

        return ResponseEntity.ok("User created");
    }
}


