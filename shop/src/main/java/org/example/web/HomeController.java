package org.example.web;

import lombok.RequiredArgsConstructor;
import org.example.entities.User;
import org.example.storage.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeController {
    private final StorageService storageService;
    private static List<User> users= new ArrayList<>();
    @GetMapping("/")
    public List<User> index() {
        return users;
    }
    @PostMapping("/create")
    public String add(@RequestBody User user) {
        //зберігаємо на сервер фото - результат ім'я фото на сервері в папці
        String fileName = storageService.store(user.getImage());
        user.setImage(fileName);
        users.add(user);
        return "Ok";
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
