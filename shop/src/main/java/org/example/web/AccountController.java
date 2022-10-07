package org.example.web;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.example.configuration.captcha.CaptchaSettings;
import org.example.configuration.captcha.GoogleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.example.DTO.UserDTO.account.LoginDTO;
import org.example.DTO.UserDTO.account.LoginSuccessDTO;
import org.example.configuration.security.JwtTokenUtil;
import org.example.entities.UserEntity;
import org.example.repositories.RoleRepository;
import org.example.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestOperations;

//import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="api/account")
public class AccountController {
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final CaptchaSettings captchaSettings;
    @Autowired
    private final RestOperations restTemplate;
    protected static final String RECAPTCHA_URL_TEMPLATE = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

//    @RequestMapping(
//            method = RequestMethod.POST,
//            value = "/login",
//            produces = "application/json; charset=UTF-8")
//    @ResponseStatus(HttpStatus.CREATED)
//    @ResponseBody
//    @ApiOperation(value = "Login user",
//            notes = "This method login user")

//    @ApiParam(
//            name =  "email",
//            type = "String",
//            value = "Email",
//            example = "ooo.kov@gmail.com",
//            required = true)
    @PostMapping("login")
    public ResponseEntity<LoginSuccessDTO> login(@RequestBody LoginDTO loginDTO) {     //@Valid

        try{
            //в url записуємо токен, що нам прийшов
            String url = String.format(RECAPTCHA_URL_TEMPLATE, captchaSettings.getSecretkey(), loginDTO.getRecaptchaToken());
            try {
                //посилаємо запит на сервер
                final GoogleResponse googleResponse = restTemplate.getForObject(url, GoogleResponse.class);
                if (!googleResponse.isSuccess()) {   //перевіряємо чи запит успішний
                    throw new Exception("reCaptcha was not successfully validated");
                }
            }
            catch (Exception rce) {
                String str = rce.getMessage();
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            LoginSuccessDTO loginUser = loginUser(loginDTO.getEmail(), loginDTO.getPassword());
            return  ResponseEntity.ok()
                    .body(loginUser);
        }catch(BadCredentialsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private LoginSuccessDTO loginUser(String username, String password) throws BadCredentialsException {
        //проводимо авторизацію користувача
        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        User user = (User) auth.getPrincipal();
        UserEntity dbUser = userRepository.findByEmail(username);
        LoginSuccessDTO loginSuccessDTO = new LoginSuccessDTO();
        loginSuccessDTO.setUsername(dbUser.getEmail());
        loginSuccessDTO.setToken((jwtTokenUtil.generateAccessToken(dbUser)));

        return  loginSuccessDTO;
    }
}
