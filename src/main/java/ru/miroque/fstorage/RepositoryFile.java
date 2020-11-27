package ru.miroque.fstorage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryFile extends JpaRepository<File, Long> {
}
