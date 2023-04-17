package co.com.perficient.project3.entity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author : Juank544
 * @date : 14/04/2023
 **/
@Getter
@Setter
@NoArgsConstructor
public class People {

    private Long id;
    private String name;
    private String nationality;
    private LocalDate birthDate;
    private Team team;
}
