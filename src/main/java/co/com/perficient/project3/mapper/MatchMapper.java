package co.com.perficient.project3.mapper;

import co.com.perficient.project3.model.dto.MatchDTO;
import co.com.perficient.project3.model.entity.Match;
import co.com.perficient.project3.service.StadiumService;
import co.com.perficient.project3.service.TeamService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class MatchMapper {

    @Autowired
    protected StadiumService stadiumService;
    @Autowired
    protected TeamService teamService;

    @Mapping(target = "stadium", expression = "java(java.util.Objects.nonNull(matchDTO.stadium()) ? stadiumService.findByName(matchDTO.stadium()).orElse(null) : null)")
    @Mapping(target = "homeTeam", expression = "java(teamService.findByName(matchDTO.homeTeam()).orElse(null))")
    @Mapping(target = "awayTeam", expression = "java(teamService.findByName(matchDTO.awayTeam()).orElse(null))")
    public abstract Match toEntity(MatchDTO matchDTO);

    @Mapping(target = "stadium", source = "stadium.name")
    @Mapping(target = "homeTeam", source = "homeTeam.name")
    @Mapping(target = "awayTeam", source = "awayTeam.name")
    public abstract MatchDTO toDTO(Match match);
}
