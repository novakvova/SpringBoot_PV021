package org.example.dto.account;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class LoginDto {
    @NotNull(message = "Вкажіть пошту")
    @Email(message = "Пошту вказано не коректно")
    private String username;
    @NotNull(message = "Вкажіть пароль")
    private String password;
}
