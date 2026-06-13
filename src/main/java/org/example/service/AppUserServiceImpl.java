package org.example.service;

import org.example.Dto.AppUserDto;
import org.example.Dto.AppUserResponseDto;
import org.example.Dto.AppUserViewDto;
import org.example.Dto.UpdateAppUserDto;
import org.example.domain.Role;
import org.example.domain.UserStatus;
import org.example.domain.VerificationToken;
import org.example.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.domain.AppUser;
import org.example.repository.AppUserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AppUserServiceImpl implements AppUserService{

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

        user.setStatus(UserStatus.INACTIVE);

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

    @Transactional
    public List<AppUserViewDto> listAllAppUsers() {

        List<AppUser> allAppUsers = appUserRepository.findByStatusOrderByIdAsc(UserStatus.ACTIVE);
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
    public void deleteUser(long id) {
        AppUser appUser = appUserRepository.findById(id);
        appUser.setStatus(UserStatus.DELETED);
    }

    public AppUser toggleAdminRole(long id){
        AppUser user = appUserRepository.findById(id);

        if(user.getRole() == Role.ROLE_ADMIN){
            user.setRole(Role.ROLE_USER);
        } else{
            user.setRole(Role.ROLE_ADMIN);
        }

        return appUserRepository.save(user);
    }

    public AppUserResponseDto editUser(String email, UpdateAppUserDto dto) {

        AppUser user = appUserRepository.findByEmail(email).orElseThrow();

        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setEmail(dto.email());
        user.setTelephone(dto.telephone());

        if (dto.password() != null && !dto.password().isBlank()) {
            if (!dto.password().equals(dto.confirmPassword())) {
                throw new IllegalArgumentException("Passwords do not match");
            }

            user.setPassword(passwordEncoder.encode(dto.password()));
        }

        AppUser saved = appUserRepository.save(user);

        return new AppUserResponseDto(
                saved.getId(),
                saved.getEmail(),
                saved.getFirstName(),
                saved.getLastName(),
                saved.getTelephone()
        );
    }

    public AppUserResponseDto getUser(String email) {
        AppUser user = appUserRepository.findByEmail(email).orElseThrow();

        return new AppUserResponseDto(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getTelephone()
        );
    }

}


