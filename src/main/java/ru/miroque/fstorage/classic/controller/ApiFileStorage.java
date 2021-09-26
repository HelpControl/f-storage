package ru.miroque.fstorage.classic.controller;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.miroque.fstorage.classic.dto.DtoFileStorageDefault;
import ru.miroque.fstorage.classic.exception.NotFoundException;
import ru.miroque.fstorage.classic.service.ServiceFileStorage;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiFileStorage {
    private final ServiceFileStorage serviceFileStorage;
    private final ObjectNode objectNode = JsonNodeFactory.instance.objectNode();

    @GetMapping("/{uuid}")
    public ResponseEntity<ObjectNode> getItemByUuid(@PathVariable UUID uuid) throws NotFoundException {
        log.info("-> getItemByUuid::{}", uuid);
        DtoFileStorageDefault dto = serviceFileStorage.findByUuid(uuid);
        objectNode.put("uuid", dto.getUuid().toString());
        log.info("<- getItemByUuid::{}", uuid);
        return new ResponseEntity<>(objectNode, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ObjectNode> uuidHandleFileUpload(@RequestParam MultipartFile file) throws IOException {
        log.info("-> handleFileUpload");
        DtoFileStorageDefault dto = serviceFileStorage.save(file);
        objectNode.put("uuid", dto.getUuid().toString());
        log.info("<- handleFileUpload uuid::{}", dto.getUuid());
        return new ResponseEntity<>(objectNode, HttpStatus.OK);
    }

}
