package ru.miroque.fstorage.classic.service;

import org.springframework.web.multipart.MultipartFile;
import ru.miroque.fstorage.classic.dto.DtoFileStorageDefault;

import java.util.UUID;

public interface ServiceFileStorage {
    DtoFileStorageDefault save(MultipartFile file);
    DtoFileStorageDefault findByUuid(UUID uuid);
}
