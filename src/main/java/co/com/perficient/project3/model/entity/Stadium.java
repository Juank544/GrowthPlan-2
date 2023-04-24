package co.com.perficient.project3.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Stadium {

    @Id
    private String id;
    private String name;
    private String country;
    private String city;
    private String capacity;

    @PrePersist
    public void prePersist() {
        if (Objects.isNull(this.id)) {
            this.id = UUID.randomUUID().toString();
        }
    }
}
