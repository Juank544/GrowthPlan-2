package co.com.perficient.project3.model.dto;

import java.io.Serializable;

public record TeamDTO(String name, String country, String stadium, String president) implements Serializable {
}
