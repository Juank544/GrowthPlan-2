package co.com.perficient.project3.entity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author : Juank544
 * @date : 14/04/2023
 **/
@Getter
@Setter
@NoArgsConstructor
public class Team {

    private Long id;
    private String name;
    private String country;
    private Integer points;
    private Stadium stadium;
    private People president;
    private People coach;
    private List<Player> players;
}
