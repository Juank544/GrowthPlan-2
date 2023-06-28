package co.com.perficient.project3.model.dto;

import java.io.Serializable;

public record StandingDTO(String team, Integer matchesPlayed, Integer wins, Integer draws, Integer losses,
                          Integer points) implements Serializable {
}
