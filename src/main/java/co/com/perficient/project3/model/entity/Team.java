package co.com.perficient.project3.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Team {

    @Id
    private Long id;
    private String name;
    private String country;
    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_STADIUM_ID"))
    private Stadium stadium;
    @OneToOne(mappedBy = "team")
    private President president;
    @OneToOne(mappedBy = "team")
    private Coach coach;
    @OneToMany(mappedBy = "team")
    private List<Player> players;
}
