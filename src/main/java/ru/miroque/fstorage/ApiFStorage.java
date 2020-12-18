package ru.miroque.fstorage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/f-storage")
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

//    @PostMapping(value = "/", consumes = {"text/plain"})
    @PostMapping("/")
    public String UUIDhandleFileUpload(@RequestParam MultipartFile file) {
        log.info("-> handleFileUpload");
        File item = new File();
        item.setMime(file.getContentType());
        item.setName(file.getOriginalFilename());
        item.setPinned(Boolean.FALSE);
        item.setUuid(UUID.randomUUID());
        item = rFile.saveAndFlush(item);
        log.info("<- handleFileUpload::id::{}::uuid::{}",item.getId(),item.getUuid());
        return item.getUuid().toString();
    }

    //COPY-PASTE:: https://spring.io/guides/gs/uploading-files/
    /*@ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(NotFoundException exc) {
        return ResponseEntity.notFound().build();
    }*/

}
