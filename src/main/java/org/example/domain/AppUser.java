package org.example.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name="app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotNull
    @Size(min=2, max=30)
    private String firstName;

    @NotNull
    @Size(min=2, max=30)
    private String lastName;

    @NotNull
    @Column(unique = true)
    private String email;

    @Size(min=9, max=9)
    private String telephone;

    @NotNull
    private String password;

    @NotNull
    private boolean enabled;

    @Enumerated(EnumType.STRING)
    private Role role;

//    @ManyToMany(fetch = FetchType.EAGER)
//    private Set<AppUserRole> appUserRole = new HashSet<AppUserRole>(0);

}
