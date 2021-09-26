package ru.miroque.fstorage.classic.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ServiceFileIODefault implements ServiceFileIO {

    @Value("${file.storage.path}")
    private String fileTemporaryPath;

    @Override
    public void writeToTemporaryPlace(MultipartFile file, String fileName) throws IOException {
        File temporaryFile = new File(fileTemporaryPath+fileName);
        file.transferTo(temporaryFile);
    }
}
