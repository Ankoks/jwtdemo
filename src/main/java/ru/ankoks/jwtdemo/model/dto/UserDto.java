package ru.ankoks.jwtdemo.model.dto;

import lombok.Data;

/**
 * User: ankoks
 * Date: 05/07/2019
 */
@Data
public class UserDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
