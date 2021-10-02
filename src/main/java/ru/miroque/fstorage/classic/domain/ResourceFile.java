package ru.miroque.fstorage.classic.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter

@Entity
public class ResourceFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private String name;
    private String mime;
    private Boolean pinned;
    private LocalDateTime created;
    private String storePath;

    @Override
    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        }
        return 13;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ResourceFile)) {
            return false; // null or other class
        }
        ResourceFile other = (ResourceFile) obj;

        if (id != null) {
            return id.equals(other.id);
        }
        return super.equals(other);
    }

}
