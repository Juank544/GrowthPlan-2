package co.com.perficient.project3.mapper;

import co.com.perficient.project3.model.dto.PlayerDTO;
import co.com.perficient.project3.model.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = TeamMapper.class)
public interface PlayerMapper {

    @Mapping(target = "team", source = "playerDTO.team", qualifiedByName = "settingTeam", conditionExpression = "java(java.util.Objects.nonNull(playerDTO.team()))")
    Player toEntity(PlayerDTO playerDTO);

    @Mapping(target = "team", source = "team.name")
    PlayerDTO toDTO(Player player);
}
