package co.com.perficient.project3.model.dto;

import java.io.Serializable;

public record PlayerDTO(String name, String nationality, String birthDate, String number, String position,
                        String team) implements Serializable {
}
