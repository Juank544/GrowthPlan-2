package co.com.perficient.project3.entity.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Coach extends Person {

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_TEAM_ID"))
    private Team team;
}
