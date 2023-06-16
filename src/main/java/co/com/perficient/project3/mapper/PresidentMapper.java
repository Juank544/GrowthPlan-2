package co.com.perficient.project3.mapper;

import co.com.perficient.project3.model.dto.PresidentDTO;
import co.com.perficient.project3.model.entity.President;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = TeamMapper.class)
public interface PresidentMapper {

    @Mapping(target = "team", source = "presidentDTO.team", qualifiedByName = "settingTeam", conditionExpression = "java(java.util.Objects.nonNull(presidentDTO.team()))")
    President toEntity(PresidentDTO presidentDTO);

    @Mapping(target = "team", source = "team.name")
    PresidentDTO toDTO(President president);
}
