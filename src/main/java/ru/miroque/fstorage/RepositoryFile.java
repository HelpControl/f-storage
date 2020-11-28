package ru.miroque.fstorage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RepositoryFile extends JpaRepository<File, Long> {
    Optional<File> findByUuid(UUID uuid);
}
