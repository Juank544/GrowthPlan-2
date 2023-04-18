package co.com.perficient.project3.entity.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
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
public class People {

    @Id
    private Long id;
    private String name;
    private String nationality;
    private LocalDate birthDate;
    @OneToOne
    @JoinColumn(referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_TEAM_ID"))
    private Team team;
}
