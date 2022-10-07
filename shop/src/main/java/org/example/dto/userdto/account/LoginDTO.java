package org.example.DTO.UserDTO.account;


import antlr.ANTLRStringBuffer;
import lombok.Data;

//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotNull;

@Data
public class LoginDTO {
//    @NotNull(message = "Вкажіть пошту")
//    @Email(message = "Пошту вказано некоректно")
    private String email;
//    @NotNull(message = "Вкажіть пароль")
    private String password;
    private String recaptchaToken;
}
