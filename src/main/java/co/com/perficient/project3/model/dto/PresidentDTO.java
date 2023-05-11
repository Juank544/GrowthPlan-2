package co.com.perficient.project3.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PresidentDTO implements Serializable {
    private String name;
    private String nationality;
    private String birthDate;
    private String team;
}
