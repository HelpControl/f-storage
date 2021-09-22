package ru.miroque.fstorage.classic.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.miroque.fstorage.classic.repository.RepositoryResourceFile;

@Slf4j
@Component
public class ScheduleFileRemover {
    private RepositoryResourceFile repositoryFile;

    @Scheduled(cron = "0/30 * * ? * *")
    public void removeUnpinnedStorageFiles(){
      log.info("Here we remove all unpinned files");
    }
}
