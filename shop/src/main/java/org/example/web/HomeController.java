package org.example.web;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.example.dto.userdto.UserCreateDTO;
import org.example.dto.userdto.UserItemDTO;
import org.example.entities.UserEntity;
import org.example.mapper.ApplicationMapper;
import org.example.repositories.UserRepository;
import org.example.storage.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api( tags = "Користувачі")
public class HomeController {
    private final StorageService storageService;
    //private static List<User> users= new ArrayList<>();
    private final UserRepository userRepository;
    private final ApplicationMapper mapper;
    @GetMapping("/")
    public List<UserItemDTO> index() {
        List<UserItemDTO> users = mapper.usersToUserItemDTO_List(userRepository.findAll());
        try {
            Thread.sleep(2000);
        }
        catch(Exception ex) {
            System.out.println("Bad thread "+ ex.getMessage());
        }
        return users;
    }
    @PostMapping("/create")

    public String add(@RequestBody UserCreateDTO user) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //зберігаємо на сервер фото - результат ім'я фото на сервері в папці
        String fileName = storageService.store(user.getImage());
        UserEntity userEntity = mapper.userCreateDtoToUserEntity(user);
        userEntity.setImage(fileName);
        userEntity.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(userEntity);
        return "Ok";
    }
    @PutMapping("/update/{id}")
    public String updateUser(@RequestBody UserEntity newUser, @PathVariable int id)
    {
        //User user = users.stream().filter(u->(u.getId()==id)).findFirst().orElse(null);
        UserEntity user = userRepository.findById(id).get();
        //User user = users.get(id);
        user.setEmail(newUser.getEmail());
        user.setImage(newUser.getImage());
        user.setAge(newUser.getAge());
        user.setPhone(newUser.getPhone());
        user.setPassword(newUser.getPassword());

        //users.set(id, user);
        userRepository.save(user);
        return "Ok";
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable int id){
        //User user = users.get(id);
        //User user = users.stream().filter(u->(u.getId()==id)).findFirst().orElse(null);
        UserEntity user = userRepository.findById(id).get();
        storageService.removeFile(user.getImage());
        //users.remove(user);
        userRepository.delete(user);

    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws Exception {

        Resource file = storageService.loadAsResource(filename);
        String urlFileName =  URLEncoder.encode("сало.jpg", StandardCharsets.UTF_8.toString());
        return ResponseEntity.ok()
                //.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION,"filename=\""+urlFileName+"\"")
                .body(file);
    }
}
