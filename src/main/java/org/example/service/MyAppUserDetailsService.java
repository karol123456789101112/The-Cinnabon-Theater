package org.example.service;

import org.example.domain.Role;
import org.example.domain.UserStatus;
import org.example.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("myAppUserDetailsService")
public class MyAppUserDetailsService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public MyAppUserDetailsService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        org.example.domain.AppUser appUser = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Użytkownik nie znaleziony: " + email));

        List<GrantedAuthority> authorities = buildUserAuthority(appUser.getRole());

        return buildUserForAuthentication(appUser, authorities);
    }

    // Converts AppUser user to org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(org.example.domain.AppUser appUser, List<GrantedAuthority> authorities) {
        return new User(appUser.getEmail(), appUser.getPassword(), appUser.getStatus() == UserStatus.ACTIVE,
                true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Role role) {
        return List.of(
                new SimpleGrantedAuthority(role.name())
        );
    }
}

