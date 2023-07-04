package co.com.perficient.project3.model.dto;

import org.springframework.hateoas.Links;

import java.io.Serializable;

public record StandingDTO(String team, Integer matchesPlayed, Integer wins, Integer draws, Integer losses,
                          Integer points, Links links) implements Serializable {
}
