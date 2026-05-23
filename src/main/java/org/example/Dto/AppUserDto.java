package org.example.Dto;

import lombok.Data;

@Data
public class AppUserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private String password;
    private String confirmPassword;
}
