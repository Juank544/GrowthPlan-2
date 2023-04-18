package co.com.perficient.project3.entity.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : Juank544
 * @date : 14/04/2023
 **/
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Team {

    @Id
    private Long id;
    private String name;
    private String country;
    private Integer points;
    @OneToOne
    @JoinColumn(referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_STADIUM_ID"))
    private Stadium stadium;
    @OneToOne(mappedBy = "team")
    private People president;
    @OneToOne(mappedBy = "team")
    private People coach;
    @OneToMany(mappedBy = "team")
    private List<Player> players;
}
