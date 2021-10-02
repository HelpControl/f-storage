package ru.miroque.fstorage.classic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Slf4j
@Component
@RequiredArgsConstructor
public class UtilFileStorage {
    @Value("${file.storage.path}")
    private String storagePath;

    @Value("${file.storage.tmp}")
    private String temporaryStoragePath;

    public void initializeStorage(){
        File storage = new File(storagePath);
        File temporaryStorage = new File(temporaryStoragePath);

        if (storage.mkdir()){
            log.info("File Storage created::[{}]", storage.getPath());
        } else {
            log.info("File Storage already exists::[{}]", storage.getPath());
        }

        if (temporaryStorage.mkdir()){
            log.info("File Storage created::[{}]", temporaryStorage.getPath());
        } else {
            log.info("File Storage already exists::[{}]", temporaryStorage.getPath());
        }
    }
}
