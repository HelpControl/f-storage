package ru.miroque.fstorage.classic.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Slf4j
@Service
public class ServiceFileIoDefault implements ServiceFileIo {

    @Value("${file.storage.tmp}")
    private String fileTemporaryPath;

    @Autowired
    ServletContext context;

    @Override
    public String storeToTemporaryPlace(MultipartFile file, String fileName) throws IOException {
        log.debug(" _ fileTemporaryPath::{}", fileTemporaryPath);
        log.debug(" _ fileName::{}", fileName);
        File temporaryFile = new File(fileTemporaryPath + fileName);
        Files.write(temporaryFile.toPath(), file.getBytes());
        return temporaryFile.getPath();
    }
}
