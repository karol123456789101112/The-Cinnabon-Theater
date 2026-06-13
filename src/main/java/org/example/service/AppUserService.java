package org.example.service;

import org.example.Dto.AppUserDto;
import org.example.Dto.AppUserResponseDto;
import org.example.Dto.AppUserViewDto;
import org.example.Dto.UpdateAppUserDto;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.example.domain.AppUser;
import java.util.List;
import java.util.Optional;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;

public interface AppUserService {

    void addAppUser(AppUserDto appUserDto);

    List<AppUserViewDto> listAllAppUsers();
    void deleteUser(long id);
    AppUser toggleAdminRole(long id);

    AppUserResponseDto editUser(String email, UpdateAppUserDto dto);

    AppUserResponseDto getUser(String email);

}

