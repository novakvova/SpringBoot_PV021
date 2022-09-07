package org.example;


import org.example.services.IDatabaseSeed;
import org.example.storage.StorageProperties;
import org.example.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");
        SpringApplication.run(Main.class, args);
    }
    @Bean
    CommandLineRunner init(StorageService storageService, IDatabaseSeed databaseSeed) {
        return (args) -> {
            //storageService.deleteAll();
            try {
                databaseSeed.Seed();
                storageService.init();
            }
            catch(Exception ex) {
                System.out.println("----propblem cteate folder--------");
            }
        };
    }
}