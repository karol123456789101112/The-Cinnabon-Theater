package org.example.service;

import org.example.Dto.AppUserDto;
import org.example.Dto.AppUserViewDto;
import org.example.domain.Role;
import org.example.domain.VerificationToken;
import org.example.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.domain.AppUser;
import org.example.repository.AppUserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppUserServiceImpl implements AppUserService{

    private static final Logger log = LoggerFactory.getLogger(AppUserService.class);

    private AppUserRepository appUserRepository;
    private PasswordEncoder passwordEncoder;
    private VerificationTokenRepository verificationTokenRepository;
    private EmailService emailService;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository,
                              PasswordEncoder passwordEncoder,
                              VerificationTokenRepository verificationTokenRepository,
                              EmailService emailService) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.verificationTokenRepository = verificationTokenRepository;
        this.emailService = emailService;
    }

    @Transactional
    public void addAppUser(AppUserDto dto) {

        AppUser user = createUser(dto);
        appUserRepository.save(user);

        String token = createVerificationToken(user);

        String link = "http://localhost:8081/activate?token=" + token;

        emailService.sendMail(
                dto.email(),
                "Witaj w The Cinnabon Theater!\n" +
                        "\n" +
                        "Kliknij link aby aktywować konto:\n" +
                        "\n" +
                        link +
                        "\n" +
                        "Link ważny 24h.",
                "Account activation - The Cinnabon Theater"
        );
    }

    private AppUser createUser(AppUserDto dto) {

        if (!dto.password().equals(dto.confirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        AppUser user = new AppUser();

        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setEmail(dto.email());
        user.setTelephone(dto.telephone());

        user.setPassword(passwordEncoder.encode(dto.password()));

        user.setRole(Role.ROLE_USER);

        user.setEnabled(false);

        return user;
    }

    private String createVerificationToken(AppUser user) {

        String token = UUID.randomUUID().toString();

        VerificationToken vt = new VerificationToken();
        vt.setToken(token);
        vt.setUser(user);
        vt.setExpiryDate(LocalDateTime.now().plusMinutes(1));

        verificationTokenRepository.save(vt);

        return token;
    }


//    @Transactional
//    public void editAppUser(AppUser appUser) {
//        appUser.getAppUserRole().add(appUserRoleRepository.findByRole("ROLE_USER"));
//        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
//        appUserRepository.save(appUser);
//    }

    @Transactional
    public List<AppUserViewDto> listAllAppUsers() {

        List<AppUser> allAppUsers = appUserRepository.findAll();
        List<AppUserViewDto> allAppUsersDto = new ArrayList<>();

        for(AppUser appUser : allAppUsers){
            AppUserViewDto dto = new AppUserViewDto(
                    appUser.getId(),
                    appUser.getFirstName(),
                    appUser.getLastName(),
                    appUser.getEmail(),
                    appUser.getRole()
            );

            allAppUsersDto.add(dto);
        }

        return allAppUsersDto;
    }

    @Transactional
    public void removeAppUser(long id) {
        appUserRepository.deleteById(id);
    }

    @Transactional
    public AppUser getAppUser(long id) {
        return appUserRepository.findById(id);
    }

    public Optional<AppUser> findByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

}


