package ru.miroque.fstorage.classic.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ServiceFileIo {
    String storeToTemporaryPlace(MultipartFile file, String fileName) throws IOException;
}
