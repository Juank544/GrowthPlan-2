package co.com.perficient.project3.entity.model;

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
public class Stadium {

    private Long id;
    private String name;
    private String country;
    private String city;
    private String capacity;
}
