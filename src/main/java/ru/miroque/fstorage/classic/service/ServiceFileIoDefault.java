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

    @Value("${file.storage.path}")
    private String fileTemporaryPath;

    @Autowired
    ServletContext context;

    @Override
    public String storeToTemporaryPlace(MultipartFile file, String fileName) throws IOException {
        log.debug(" _ fileTemporaryPath::{}", fileTemporaryPath);
        log.debug(" _ fileName::{}", fileName);
        log.debug(" _ classpath::{}", context.getRealPath("resources/uploads"));
        File temporaryFile = new File(fileTemporaryPath + fileName);
        Files.write(temporaryFile.toPath(), file.getBytes());
//        file.transferTo(temporaryFile);
        return temporaryFile.getPath();
    }
}
