package org.example.dto.account;

import lombok.Data;
import org.springframework.data.mapping.KPropertyPathExtensionsKt;


@Data
public class LoginSuccessDto {
    private String username;
    private String token;
}
