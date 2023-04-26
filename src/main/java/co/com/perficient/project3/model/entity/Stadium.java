package co.com.perficient.project3.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
public class Stadium {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String country;
    private String city;
    private String capacity;
}
