package co.com.perficient.project3.mapper;

import co.com.perficient.project3.model.dto.StadiumDTO;
import co.com.perficient.project3.model.entity.Stadium;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper
public interface StadiumMapper {
    Stadium toEntity(StadiumDTO stadiumDTO);

    @InheritInverseConfiguration
    StadiumDTO toDTO(Stadium stadium);
}
