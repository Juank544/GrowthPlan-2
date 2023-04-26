package co.com.perficient.project3.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class StadiumDTO implements Serializable {
    private String name;
    private String country;
    private String city;
    private String capacity;
}
