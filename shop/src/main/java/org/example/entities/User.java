package org.example.entities;

import lombok.Data;

@Data
public class User {
    private int id;
    private String email;
    private String phone;
    private String image;
    private String password;
    private int age;
}
