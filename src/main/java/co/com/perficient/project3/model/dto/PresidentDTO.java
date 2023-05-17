package co.com.perficient.project3.model.dto;

import java.io.Serializable;

public record PresidentDTO(String name, String nationality, String birthDate, String team) implements Serializable {
}
