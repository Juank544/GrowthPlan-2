package co.com.perficient.project3.entity.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Player extends Person {

    private String number;
    private String position;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_TEAM_ID"))
    private Team team;
}
