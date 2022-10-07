package org.example.DTO.UserDTO;

import lombok.Data;

@Data
public class UserCreateDTO {
    private String email;
    private String phone;
    private String image;
    private String password;
    private int age;
}
