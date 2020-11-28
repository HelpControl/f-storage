package ru.miroque.fstorage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
