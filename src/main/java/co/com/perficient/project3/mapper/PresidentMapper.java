package co.com.perficient.project3.mapper;

import co.com.perficient.project3.model.dto.PresidentDTO;
import co.com.perficient.project3.model.entity.President;
import co.com.perficient.project3.service.TeamService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class PresidentMapper {

    @Autowired
    protected TeamService teamService;

    @Mapping(target = "team", expression = "java(teamService.findByName(presidentDTO.team()).orElse(null))")
    public abstract President toEntity(PresidentDTO presidentDTO);

    @Mapping(target = "team", source = "team.name")
    public abstract PresidentDTO toDTO(President president);
}
