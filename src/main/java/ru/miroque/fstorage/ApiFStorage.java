package ru.miroque.fstorage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/f-storage")
public class ApiFStorage {
    private final RepositoryFile rFile;

    public ApiFStorage(RepositoryFile rFile) {
        this.rFile = rFile;
    }

    @GetMapping("/")
    public ResponseEntity<List<File>> all() {
        log.info("-> all");
        List<File> items = rFile.findAll();
        log.info("<- all");
        return ResponseEntity.ok(items);
    }

}
