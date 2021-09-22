package ru.miroque.fstorage.classic.dto;

import lombok.Data;
import ru.miroque.fstorage.classic.domain.ResourceFile;

import java.util.UUID;

@Data
public class DtoFileStorageDefault {
    private UUID uuid;
    private String name;
    private String mime;
    private Boolean pinned;

    public DtoFileStorageDefault(ResourceFile item) {
        this.uuid = item.getUuid();
        this.name = item.getName();
        this.mime = item.getMime();
        this.pinned = item.getPinned();
    }
}
