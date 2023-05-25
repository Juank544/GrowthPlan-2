package co.com.perficient.project3.mapper;

import co.com.perficient.project3.model.dto.CoachDTO;
import co.com.perficient.project3.model.entity.Coach;
import co.com.perficient.project3.service.TeamService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class CoachMapper {

    @Autowired
    protected TeamService teamService;

    @Mapping(target = "team", expression = "java(teamService.findByName(coachDTO.team()).orElse(null))")
    public abstract Coach toEntity(CoachDTO coachDTO);

    @Mapping(target = "team", source = "team.name")
    public abstract CoachDTO toDTO(Coach coach);
}
