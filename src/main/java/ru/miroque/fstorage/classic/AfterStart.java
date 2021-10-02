package ru.miroque.fstorage.classic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AfterStart implements CommandLineRunner {

    @Autowired
    private UtilFileStorage utilFileStorage;

    @Override
    public void run(String... args) throws Exception {
        log.info("<--- Initialization started --->");
        utilFileStorage.initializeStorage();
        log.info("<--- Initialization completed --->");
    }
}
