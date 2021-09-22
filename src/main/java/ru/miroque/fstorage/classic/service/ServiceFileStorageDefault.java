package ru.miroque.fstorage.classic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ru.miroque.fstorage.classic.domain.ResourceFile;
import ru.miroque.fstorage.classic.dto.DtoFileStorageDefault;
import ru.miroque.fstorage.classic.exception.NotFoundException;
import ru.miroque.fstorage.classic.repository.RepositoryResourceFile;

import java.util.UUID;

@RequiredArgsConstructor
public class ServiceFileStorageDefault implements ServiceFileStorage {
    private final RepositoryResourceFile repositoryResourceFile;
    @Override
    public DtoFileStorageDefault save(MultipartFile file) {
        ResourceFile item = new ResourceFile();
        item.setMime(file.getContentType());
        item.setName(file.getOriginalFilename());
        item.setPinned(Boolean.FALSE);
        item.setUuid(UUID.randomUUID());
        item = repositoryResourceFile.save(item);
        return new DtoFileStorageDefault(item);
    }

    @Override
    public DtoFileStorageDefault findByUuid(UUID uuid) {
        return new DtoFileStorageDefault(repositoryResourceFile.findByUuid(uuid).orElseThrow(() -> new NotFoundException("File with uuid::" + uuid + " not found")));
    }
}
