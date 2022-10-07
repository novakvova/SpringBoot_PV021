package org.example.service;

import org.example.entities.UserEntity;
import org.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByEmail(username); //шукаємо юзера в БД
        if(user==null) //якщо немає, то exeption
            throw new UsernameNotFoundException("Email "+ username +" not found");
                                                             //права юзера по ролі
        return new User(user.getEmail(), user.getPassword(), getAuthorities(user)); // якщо є, то створюється новий юзер на основі того, що в БД
    //юзер має email, password, і список ролей, які витягуються у методі нижче
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(UserEntity user) { //метод приймає юзера
        String [] userRoles = user.getRoles().stream()                                      //витягується списочок ролей, які є у юзера
                .map((role) -> role.getName()).toArray(String []:: new);
        Collection<GrantedAuthority> authorityCollections =                               //створюється нова колекція authorityCollections
                AuthorityUtils.createAuthorityList(userRoles);
        return authorityCollections;                                                       //повертається ця колекція
    }
}