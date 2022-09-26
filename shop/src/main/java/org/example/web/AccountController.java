package org.example.web;

import lombok.RequiredArgsConstructor;
import org.example.configuration.security.JwtTokenUtil;
import org.example.dto.account.LoginDto;
import org.example.dto.account.LoginSuccessDto;
import org.example.entities.UserEntity;
import org.example.repositories.RoleRepository;
import org.example.repositories.UserRepository;
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

//import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="api/account")
public class AccountController {
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager  authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @PostMapping("login")
    public ResponseEntity<LoginSuccessDto> login(@RequestBody  LoginDto loginDto) {
        try {
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
