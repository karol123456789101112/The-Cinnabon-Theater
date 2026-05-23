package org.example.domain;

import jakarta.persistence.*;

@Entity
@Table(name="app_user_role")
public class AppUserRole {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String role;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

}

