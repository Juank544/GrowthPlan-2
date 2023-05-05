package co.com.perficient.project3.service.impl;

import co.com.perficient.project3.model.entity.Stadium;
import co.com.perficient.project3.repository.StadiumRepository;
import com.querydsl.core.types.Predicate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class StadiumServiceImplTest {

    @InjectMocks
    private StadiumServiceImpl stadiumService;
    @Mock
    private StadiumRepository stadiumRepository;

    final UUID ID_STADIUM = UUID.randomUUID();
    final String NAME = "stadiumName";

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void create() {
        Stadium stadium = Stadium.builder().id(ID_STADIUM).build();

        when(stadiumRepository.save(any(Stadium.class))).thenReturn(stadium);

        Stadium stadiumCreated = stadiumService.create(stadium);
        Assertions.assertThat(stadiumCreated).isNotNull();
        Assertions.assertThat(stadiumCreated.getId()).isEqualTo(ID_STADIUM);
    }

    @Test
    void findAll() {
        when(stadiumRepository.findAll()).thenReturn(Collections.singletonList(Stadium.builder().build()));

        List<Stadium> stadiums = stadiumService.findAll();
        Assertions.assertThat(stadiums).isNotNull().isNotEmpty();
    }

    @Test
    void findById() {
        when(stadiumRepository.findById(any(UUID.class))).thenReturn(Optional.of(Stadium.builder().id(ID_STADIUM).build()));

        Optional<Stadium> optionalStadium = stadiumService.findById(ID_STADIUM);
        Assertions.assertThat(optionalStadium).isNotNull().isPresent();
    }

    @Test
    void update() {
        Stadium oldStadium = Stadium.builder().build();
        final String COUNTRY = "colombia";
        final String CITY = "bogota";
        final String CAPACITY = "2000";
        Stadium newStadium = Stadium.builder().name(NAME).country(COUNTRY).city(CITY).capacity(CAPACITY).build();

        when(stadiumRepository.saveAndFlush(any(Stadium.class))).thenReturn(oldStadium);

        Stadium stadiumUpdated = stadiumService.update(oldStadium, newStadium);
        Assertions.assertThat(stadiumUpdated).isNotNull();
        Assertions.assertThat(stadiumUpdated.getName()).isEqualTo(NAME);
        Assertions.assertThat(stadiumUpdated.getCountry()).isEqualTo(COUNTRY);
        Assertions.assertThat(stadiumUpdated.getCity()).isEqualTo(CITY);
        Assertions.assertThat(stadiumUpdated.getCapacity()).isEqualTo(CAPACITY);
    }

    @Test
    void delete() {
        stadiumService.delete(UUID.randomUUID());
        verify(stadiumRepository, times(1)).deleteById(any(UUID.class));
    }

    @Test
    void patch() {
        Stadium stadium = Stadium.builder().build();
        Map<String, Object> fields = new HashMap<>();

        fields.put("name", NAME);
        when(stadiumRepository.saveAndFlush(any(Stadium.class))).thenReturn(stadium);

        Stadium stadiumPartialUpdated = stadiumService.patch(stadium, fields);
        Assertions.assertThat(stadiumPartialUpdated).isNotNull();
        Assertions.assertThat(stadiumPartialUpdated.getName()).isEqualTo(NAME);
    }

    @Test
    void findByName() {
        when(stadiumRepository.findOne(any(Predicate.class))).thenReturn(Optional.of(Stadium.builder().name(NAME)
                .build()));

        Optional<Stadium> optionalStadium = stadiumService.findByName(NAME);
        Assertions.assertThat(optionalStadium).isNotNull();
    }
}
