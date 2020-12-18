package ru.miroque.fstorage;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ApiAppealImpl implements ApiAppeal {
    @Override
    public String updateOrCreate(MultipartFile file) {
         log.info("User create or update appeal ->");
         log.info("User create or update appeal <-");
        return "gggggg---fffff";
    }

}
