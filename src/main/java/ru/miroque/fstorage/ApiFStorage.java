package ru.miroque.fstorage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/api/f-storage")
public class ApiFStorage {
    private final RepositoryFile rFile;

    public ApiFStorage(RepositoryFile rFile) {
        this.rFile = rFile;
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<File> getItemByUuid(@PathVariable UUID uuid) throws NotFoundException {
        log.info("-> getItemByUuid::{}", uuid);
        Optional<File> item = rFile.findByUuid(uuid);
        log.info("<- getItemByUuid::{}", uuid);
        return ResponseEntity.ok(item.orElseThrow(() -> new NotFoundException("File with uuid::" + uuid + " not found")));
    }

    @PostMapping("/")
    public ResponseEntity<UUID> handleFileUpload(@RequestParam MultipartFile file) {
        log.info("-> handleFileUpload");
        File item = new File();
        item.setMime(file.getContentType());
        item.setName(file.getOriginalFilename());
        item.setPinned(Boolean.FALSE);
        item.setUuid(UUID.randomUUID());
        item = rFile.saveAndFlush(item);
        log.info("<- handleFileUpload::id::{}::uuid::{}",item.getId(),item.getUuid());
        return ResponseEntity.ok(item.getUuid());
    }

    //COPY-PASTE:: https://spring.io/guides/gs/uploading-files/
    /*@ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(NotFoundException exc) {
        return ResponseEntity.notFound().build();
    }*/

}
