package org.example.web;

import lombok.RequiredArgsConstructor;
import org.example.configuration.captcha.CaptchaSettings;
import org.example.configuration.captcha.GoogleResponse;
import org.example.configuration.security.JwtTokenUtil;
import org.example.dto.account.LoginDto;
import org.example.dto.account.LoginSuccessDto;
import org.example.entities.UserEntity;
import org.example.repositories.RoleRepository;
import org.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

//import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="api/account")
public class AccountController {
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager  authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final CaptchaSettings captchaSettings;
    @Autowired
    private final RestOperations restTemplate;
    protected static final String RECAPTCHA_URL_TEMPLATE = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";
    @PostMapping("login")
    public ResponseEntity<LoginSuccessDto> login(@RequestBody  LoginDto loginDto) {
        try {
            String url = String.format(RECAPTCHA_URL_TEMPLATE, captchaSettings.getSecretkey(), loginDto.getRecaptchaToken());
            try {
                final GoogleResponse googleResponse = restTemplate.getForObject(url, GoogleResponse.class);
                if (!googleResponse.isSuccess()) {
                    throw new Exception("reCaptcha was not successfully validated");
                }
            }
            catch (Exception rce) {
                String str = rce.getMessage();
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            LoginSuccessDto loginUser = loginUser(loginDto.getEmail(), loginDto.getPassword());
            return ResponseEntity.ok()
                    .body(loginUser);
        }catch(BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private LoginSuccessDto loginUser(String username, String password) throws BadCredentialsException {
        //Проводимо валідацію пористувача
        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        User user = (User)auth.getPrincipal();
        UserEntity dbUser = userRepository.findByEmail(username);
        LoginSuccessDto loginSuccessDto = new LoginSuccessDto();
        loginSuccessDto.setUsername(dbUser.getEmail());
        loginSuccessDto.setToken(jwtTokenUtil.generateAccessToken(dbUser));

        return loginSuccessDto;
    }
}
