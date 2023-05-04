package co.com.perficient.project3.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TeamDTO implements Serializable {
    private String name;
    private String country;
    private String stadium;
}
