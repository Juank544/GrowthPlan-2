package co.com.perficient.project3.controller;

import co.com.perficient.project3.model.dto.TeamDTO;
import co.com.perficient.project3.model.entity.Stadium;
import co.com.perficient.project3.model.entity.Team;
import co.com.perficient.project3.repository.StadiumRepository;
import co.com.perficient.project3.repository.TeamRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.UUID;

import static co.com.perficient.project3.utils.constant.TeamConstants.TEAM_ENDPOINT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TeamControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private StadiumRepository stadiumRepository;

    private final UUID uuidA = UUID.fromString("1852622d-e698-41e9-b682-cf04a5ccf280");
    private final UUID uuidB = UUID.fromString("f09be61e-682b-46f5-8905-0cd71151063e");

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        Team teamA = Team.builder().id(uuidA).name("Team A").country("Country A").build();
        Team teamB = Team.builder().id(uuidB).name("Team B").country("Country B").build();
        teamRepository.saveAll(Arrays.asList(teamA, teamB));
    }

    @Test
    void createTeam() throws Exception {
        final String NAME = "Team C";
        final String COUNTRY = "Country C";

        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setName(NAME);
        teamDTO.setCountry(COUNTRY);
        teamDTO.setStadium("");
        String body = new ObjectMapper().writeValueAsString(teamDTO);

        MvcResult mvcResult = mockMvc.perform(post(TEAM_ENDPOINT).content(body).contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.country").value(COUNTRY)).andReturn();
    }

    @Test
    void findAllTeams() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(TEAM_ENDPOINT)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.size()").value(2)).andReturn();
    }

    @Test
    void findTeamById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(TEAM_ENDPOINT + "/{id}", uuidB)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Team B")).andReturn();
    }

    @Test
    void updateTeam() throws Exception {
        final String STADIUM_NAME = "Stadium D";
        stadiumRepository.save(Stadium.builder().name(STADIUM_NAME).build());

        final String NAME = "Team D";
        final String COUNTRY = "Country D";
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setName(NAME);
        teamDTO.setCountry(COUNTRY);
        teamDTO.setStadium(STADIUM_NAME);
        String body = new ObjectMapper().writeValueAsString(teamDTO);

        MvcResult mvcResult = mockMvc.perform(put(TEAM_ENDPOINT + "/{id}", uuidA).content(body)
                        .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.country").value(COUNTRY)).andExpect(jsonPath("$.stadium").value(STADIUM_NAME))
                .andReturn();
    }

    @Test
    void deleteTeam() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete(TEAM_ENDPOINT + "/{id}", uuidA)).andDo(print()).andExpect(status().isOk())
                .andReturn();
    }
}
