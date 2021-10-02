package ru.miroque.fstorage.classic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.miroque.fstorage.classic.domain.ResourceFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RepositoryResourceFile extends JpaRepository<ResourceFile, Long> {
    Optional<ResourceFile> findByUuid(UUID uuid);

    List<ResourceFile> findAllByPinnedFalse();

    @Override
    void deleteInBatch(Iterable<ResourceFile> iterable);
}
