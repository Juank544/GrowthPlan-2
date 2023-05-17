package co.com.perficient.project3.model.dto;

import java.io.Serializable;

public record StadiumDTO(String name, String country, String city, String capacity) implements Serializable {
}
