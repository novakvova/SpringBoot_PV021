package org.example.web;

import lombok.RequiredArgsConstructor;
import org.example.configuration.security.JwtTokenUtil;
import org.example.dto.account.LoginSuccessDto;
import org.example.entities.UserEntity;
import org.example.repositories.RoleRepository;
import org.example.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="api/account")
public class AccountController {
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager  authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

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
