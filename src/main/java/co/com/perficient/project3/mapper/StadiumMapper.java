package co.com.perficient.project3.mapper;

import co.com.perficient.project3.model.dto.StadiumDTO;
import co.com.perficient.project3.model.entity.Stadium;
import org.mapstruct.Mapper;

@Mapper
public interface StadiumMapper {
    Stadium toEntity(StadiumDTO stadiumDTO);
    StadiumDTO toDTO(Stadium stadium);
}
