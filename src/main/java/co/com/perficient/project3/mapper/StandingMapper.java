package co.com.perficient.project3.mapper;

import co.com.perficient.project3.model.dto.StandingDTO;
import co.com.perficient.project3.model.entity.Standing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = TeamMapper.class)
public interface StandingMapper {

    @Mapping(target = "team", source = "team", qualifiedByName = "settingTeam", conditionExpression = "java(java.util.Objects.nonNull(standingDTO.team()))")
    @Mapping(target = "matchesPlayed", ignore = true)
    @Mapping(target = "points", ignore = true)
    Standing toEntity(StandingDTO standingDTO);

    @Mapping(target = "team", source = "team.name")
    StandingDTO toDTO(Standing standing);
}
