package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.constants.Roles;
import org.example.entities.*;
import org.example.entities.UserEntity;
import org.example.repositories.ProductRepository;
import org.example.repositories.ProductImageRepository;
import org.example.repositories.RoleRepository;
import org.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class DatabaseSeed implements IDatabaseSeed {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductImageRepository productImageRepository;

    @Override
    public void Seed() {
        if(this.roleRepository.count()==0)
        {
            this.roleRepository.save(new RoleEntity(Roles.Admin));
            this.roleRepository.save(new RoleEntity(Roles.User));
        }
        if(this.userRepository.count()==0)
        {
            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            UserEntity user = new UserEntity();
            user.setEmail("semen@gmail.com");
            user.setPhone("096-620-7377");
            user.setPassword(encoder.encode("123456"));
            user.setRoles(Arrays.asList(
                    roleRepository.findByName(Roles.Admin)));
            this.userRepository.save(user);
        }
        if(this.productRepository.count()==0)
        {
            ProductEntity pants= new ProductEntity("Штани", 402, "Для чоловіків");
            ProductEntity shirt= new ProductEntity("Сорочка", 300, "Для жінок");
            ProductEntity hat= new ProductEntity("Шляпа", 200, "Для жінок та чловіків");
            productRepository.save(pants);
            productRepository.save(shirt);
            productRepository.save(hat);

            productImageRepository.save(new ProductImageEntity("1.jpg",1,pants));
            productImageRepository.save(new ProductImageEntity("2.jpg",2,pants));
            productImageRepository.save(new ProductImageEntity("3.jpg",3,pants));

            productImageRepository.save(new ProductImageEntity("4.jpg",1,shirt));
            productImageRepository.save(new ProductImageEntity("5.jpg",2,shirt));

            productImageRepository.save(new ProductImageEntity("6.jpg",1,hat));
            productImageRepository.save(new ProductImageEntity("7.jpg",2,hat));


        }
    }
}
