package ru.miroque.fstorage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
/*
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
    }*/
//HINT: https://stackoverflow.com/questions/49457761/spring-webflux-415-with-multipartfile
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Mono<String> requestBodyFlux(@RequestBody Flux<Part> parts) {
        return partFluxDescription(parts);
    }

    private static String partMapDescription(MultiValueMap<String, Part> partsMap) {
        return partsMap.keySet().stream().sorted()
                .map(key -> partListDescription(partsMap.get(key)))
                .collect(Collectors.joining(",", "Map[", "]"));
    }

    private static Mono<String> partFluxDescription(Flux<? extends Part> partsFlux) {
        return partsFlux.log().collectList().map(ApiFStorage::partListDescription);
    }

    private static String partListDescription(List<? extends Part> parts) {
        return parts.stream().map(ApiFStorage::partDescription)
                .collect(Collectors.joining(",", "[", "]"));
    }

    private static String partDescription(Part part) {
        return part instanceof FilePart ? part.name() + ":" + ((FilePart) part).filename() : part.name();
    }

}
