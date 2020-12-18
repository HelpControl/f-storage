package ru.miroque.fstorage;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/appeals")
public interface ApiAppeal {

    @PostMapping
    String updateOrCreate(@RequestParam(required = false) MultipartFile file);
}
