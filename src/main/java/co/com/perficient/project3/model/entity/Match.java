package co.com.perficient.project3.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Match {

    @Id
    private Long id;
    private LocalDate date;
    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_STADIUM_ID"))
    private Stadium stadium;
    private String round;
    private String status;
    private String score;
    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_HOME_TEAM_ID"))
    private Team homeTeam;
    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_AWAY_TEAM_ID"))
    private Team awayTeam;
}
