package ru.miroque.fstorage.classic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(FStorageApplication.class, args);
    }

}
