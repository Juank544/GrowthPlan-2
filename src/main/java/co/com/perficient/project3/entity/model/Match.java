package co.com.perficient.project3.entity.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import java.time.LocalDate;
import java.util.Map;
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
public class Match {

    @Id
    private Long id;
    private LocalDate date;
    @OneToOne
    @JoinColumn(referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_STADIUM_ID"))
    private Stadium stadium;
    private String round;
    private String status;
    @Transient
    private Map<Long, Integer> result;
}
