package org.example.service;

import org.example.Dto.AppUserDto;
import org.example.Dto.AppUserViewDto;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.example.domain.AppUser;
import java.util.List;
import java.util.Optional;

public interface AppUserService {

    void addAppUser(AppUserDto appUserDto);

//    @PreAuthorize("hasRole('ROLE_ADMIN') OR (#appUser.login == principal.username)")
//    void editAppUser(@Param("appUser") AppUser appUser);

    List<AppUserViewDto> listAllAppUsers();

    @Secured("ROLE_ADMIN")
    void removeAppUser (long id);

    AppUser getAppUser(long id);

    Optional<AppUser> findByEmail(String email);
}

