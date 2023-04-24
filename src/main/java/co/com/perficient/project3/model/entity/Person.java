package co.com.perficient.project3.model.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
public class Person {

    @Id
    private Long id;
    private String name;
    private String nationality;
    @Past(message = "The birth date must be before from today")
    private LocalDate birthDate;
}
