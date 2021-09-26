package ru.miroque.fstorage.classic.service;

import org.springframework.web.multipart.MultipartFile;
import ru.miroque.fstorage.classic.dto.DtoFileStorageDefault;

import java.io.IOException;
import java.util.UUID;

public interface ServiceFileStorage {
    DtoFileStorageDefault save(MultipartFile file) throws IOException;
    DtoFileStorageDefault findByUuid(UUID uuid);
    Boolean pin(UUID uuid);
}
