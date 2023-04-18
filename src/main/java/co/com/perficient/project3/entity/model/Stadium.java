package co.com.perficient.project3.entity.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : Juank544
 * @date : 14/04/2023
 **/
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Stadium {

    @Id
    private Long id;
    private String name;
    private String country;
    private String city;
    private String capacity;
}
