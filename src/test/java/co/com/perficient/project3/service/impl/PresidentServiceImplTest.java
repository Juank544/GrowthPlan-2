package co.com.perficient.project3.service.impl;

import co.com.perficient.project3.model.entity.President;
import co.com.perficient.project3.repository.PresidentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class PresidentServiceImplTest {

    @InjectMocks
    private PresidentServiceImpl presidentService;
    @Mock
    private PresidentRepository presidentRepository;

    final UUID ID_PRESIDENT = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void create() {
        President president = President.builder().id(ID_PRESIDENT).build();

        when(presidentRepository.save(any(President.class))).thenReturn(president);

        President presidentCreated = presidentService.create(president);
        Assertions.assertThat(presidentCreated).isNotNull();
    }

    @Test
    void findAll() {
        when(presidentRepository.findAll()).thenReturn(Collections.singletonList(President.builder().build()));

        List<President> presidents = presidentService.findAll();
        Assertions.assertThat(presidents).isNotNull().isNotEmpty();
    }

    @Test
    void findById() {
        when(presidentRepository.findById(any(UUID.class))).thenReturn(Optional.of(President.builder().id(ID_PRESIDENT)
                .build()));

        Optional<President> optionalPresident = presidentService.findById(ID_PRESIDENT);
        Assertions.assertThat(optionalPresident).isNotNull().isPresent();
    }

    @Test
    void update() {
        President oldPresident = President.builder().build();
        final String NAME = "presidentName";
        final String NATIONALITY = "colombian";
        President newPresident = President.builder().name(NAME).nationality(NATIONALITY).birthDate(LocalDate.now())
                .build();

        when(presidentRepository.saveAndFlush(any(President.class))).thenReturn(oldPresident);

        President presidentUpdated = presidentService.update(oldPresident, newPresident);
        Assertions.assertThat(presidentUpdated).isNotNull();
        Assertions.assertThat(presidentUpdated.getName()).isEqualTo(NAME);
        Assertions.assertThat(presidentUpdated.getNationality()).isEqualTo(NATIONALITY);
        Assertions.assertThat(presidentUpdated.getBirthDate()).isNotNull();
    }

    @Test
    void delete() {
        presidentService.delete(UUID.randomUUID());
        verify(presidentRepository, times(1)).deleteById(any(UUID.class));
    }
}