package ru.miroque.fstorage;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter

@Entity
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private String name;
    private String mime;
    private Boolean pinned;

    @Override
    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        }
        return 13;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof File)) {
            return false; // null or other class
        }
        File other = (File) obj;

        if (id != null) {
            return id.equals(other.id);
        }
        return super.equals(other);
    }

}
