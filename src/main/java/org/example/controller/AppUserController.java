package org.example.controller;

import org.example.Dto.AppUserDto;
import org.example.Dto.AppUserViewDto;
import org.example.service.AppUserService;
import org.example.service.ReCaptchaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        if (!reCaptchaService.verify(dto.recaptchaToken())) {

            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Invalid reCAPTCHA");
        }

        appUserService.addAppUser(dto);

        return ResponseEntity.ok("User created");
    }

    @GetMapping("/listAllUsers")
    public List<AppUserViewDto> listAllUsers(){
        return appUserService.listAllAppUsers();
    }
}


