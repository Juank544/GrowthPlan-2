package co.com.perficient.project3.entity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;

/**
 * @author : Juank544
 * @date : 14/04/2023
 **/
@Getter
@Setter
@NoArgsConstructor
public class Match {

    private Long id;
    private LocalDate date;
    private Map<Long, Integer> result;
}
