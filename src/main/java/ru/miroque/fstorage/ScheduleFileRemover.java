package ru.miroque.fstorage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ScheduleFileRemover {
    private RepositoryFile repositoryFile;

    @Scheduled(cron = "1 0 0 * * *")
    public void removeUnpinnedStorageFiles(){
      log.info("Here we remove all unpinned files");

    }
}
