package ru.miroque.fstorage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/file-storage")
public class ApiFileStorage {
    private final RepositoryFile rFile;

    public ApiFileStorage(RepositoryFile rFile) {
        this.rFile = rFile;
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<StoragedFile> getItemByUuid(@PathVariable UUID uuid) throws NotFoundException {
        log.info("-> getItemByUuid::{}", uuid);
        Optional<StoragedFile> item = rFile.findByUuid(uuid);
        log.info("<- getItemByUuid::{}", uuid);
        return ResponseEntity.ok(item.orElseThrow(() -> new NotFoundException("File with uuid::" + uuid + " not found")));
    }
    @PostMapping("/")
    public ResponseEntity<UUID> UUIDhandleFileUpload(@RequestParam MultipartFile file) {
        log.info("-> handleFileUpload");
        StoragedFile item = new StoragedFile();
        item.setMime(file.getContentType());
        item.setName(file.getOriginalFilename());
        item.setPinned(Boolean.FALSE);
        item.setUuid(UUID.randomUUID());
        item = rFile.saveAndFlush(item);
        log.info("<- handleFileUpload::id::{}::uuid::{}",item.getId(),item.getUuid());
        return ResponseEntity.ok(item.getUuid());
    }

}
