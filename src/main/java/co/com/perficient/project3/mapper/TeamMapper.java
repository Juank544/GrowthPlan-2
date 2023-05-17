package co.com.perficient.project3.mapper;

import co.com.perficient.project3.model.dto.TeamDTO;
import co.com.perficient.project3.model.entity.Team;
import co.com.perficient.project3.service.StadiumService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class TeamMapper {

    @Autowired
    protected StadiumService stadiumService;

    @Mapping(target = "stadium", expression = "java(stadiumService.findByName(teamDTO.stadium()).orElse(null))")
    @Mapping(target = "president", ignore = true)
    public abstract Team toEntity(TeamDTO teamDTO);

    @Mapping(target = "stadium", source = "team.stadium.name")
    @Mapping(target = "president", source = "team.president.name")
    public abstract TeamDTO toDTO(Team team);
}
