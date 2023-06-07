package co.com.perficient.project3.mapper;

import co.com.perficient.project3.model.dto.CoachDTO;
import co.com.perficient.project3.model.entity.Coach;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = TeamMapper.class)
public interface CoachMapper {

    @Mapping(target = "team", source = "coachDTO.team", qualifiedByName = "settingTeam", conditionExpression = "java(java.util.Objects.nonNull(coachDTO.team()))")
    Coach toEntity(CoachDTO coachDTO);

    @Mapping(target = "team", source = "team.name")
    CoachDTO toDTO(Coach coach);
}
