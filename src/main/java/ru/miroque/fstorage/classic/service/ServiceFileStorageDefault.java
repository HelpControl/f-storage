package ru.miroque.fstorage.classic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.miroque.fstorage.classic.domain.ResourceFile;
import ru.miroque.fstorage.classic.dto.DtoFileStorageDefault;
import ru.miroque.fstorage.classic.exception.NotFoundException;
import ru.miroque.fstorage.classic.repository.RepositoryResourceFile;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ServiceFileStorageDefault implements ServiceFileStorage {
    private final RepositoryResourceFile repositoryResourceFile;
    private final ServiceFileIo serviceFileIO;

    @Transactional
    @Override
    public DtoFileStorageDefault save(MultipartFile file) throws IOException {
        UUID nameUUID = UUID.randomUUID();
        serviceFileIO.storeToTemporaryPlace(file, nameUUID.toString());
        ResourceFile item = new ResourceFile();
        item.setMime(file.getContentType());
        item.setName(file.getOriginalFilename());
        item.setPinned(Boolean.FALSE);
        item.setUuid(nameUUID);
        item = repositoryResourceFile.save(item);
        return new DtoFileStorageDefault(item);
    }

    @Override
    public DtoFileStorageDefault findByUuid(UUID uuid) {
        return new DtoFileStorageDefault(repositoryResourceFile.findByUuid(uuid).orElseThrow(() -> new NotFoundException("File with uuid::" + uuid + " not found")));
    }

    @Override
    public Boolean pin(UUID uuid) {
        return Boolean.FALSE;
    }
}
