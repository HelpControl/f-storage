package ru.miroque.fstorage.classic.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.miroque.fstorage.classic.repository.RepositoryResourceFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduleFileRemover {
    private final RepositoryResourceFile repositoryFile;

    @Value("${file.storage.tmp}")
    private String fileTemporaryPath;

    @Scheduled(cron = "0 0 */1 ? * *")
    public void removeUnpinnedStorageFiles(){
      log.info("Here we remove all unpinned files");
        try {
            Files.walk(Paths.get(fileTemporaryPath))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            log.error("{}", e);
        }
        var temporaryFilesToDelete = repositoryFile.findAllByPinnedFalse();
        if (!temporaryFilesToDelete.isEmpty()) {
            repositoryFile.deleteInBatch(temporaryFilesToDelete);
        }
    }
}
