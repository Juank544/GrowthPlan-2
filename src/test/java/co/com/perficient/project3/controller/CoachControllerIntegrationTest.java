package co.com.perficient.project3.controller;

import co.com.perficient.project3.model.dto.CoachDTO;
import co.com.perficient.project3.model.entity.Coach;
import co.com.perficient.project3.repository.CoachRepository;
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

import java.time.LocalDate;
import java.util.Arrays;

import static co.com.perficient.project3.utils.constant.CoachConstants.COACH;
import static co.com.perficient.project3.utils.constant.Constants.uuidA;
import static co.com.perficient.project3.utils.constant.Constants.uuidB;
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
class CoachControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CoachRepository coachRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        Coach coachA = Coach.builder().id(uuidA).name("Coach A").nationality("Nationality A")
                .birthDate(LocalDate.now().minusYears(1)).build();
        Coach coachB = Coach.builder().id(uuidB).name("Coach B").nationality("Nationality B")
                .birthDate(LocalDate.now().minusYears(1)).build();
        coachRepository.saveAll(Arrays.asList(coachA, coachB));
    }

    @Test
    void createCoach() throws Exception {
        final String NAME = "Coach C";
        final String NATIONALITY = "Nationality C";

        CoachDTO coachDTO = new CoachDTO(NAME, NATIONALITY, LocalDate.now().minusYears(1).toString(), "");
        String body = new ObjectMapper().writeValueAsString(coachDTO);

        MvcResult mvcResult = mockMvc.perform(post(COACH).content(body)
                        .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.nationality").value(NATIONALITY)).andExpect(jsonPath("$.birthDate").isNotEmpty())
                .andReturn();
    }

    @Test
    void findAllCoaches() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(COACH)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.size()").value(2)).andReturn();
    }

    @Test
    void findCoachById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(COACH + "/{id}", uuidB)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Coach B"))
                .andExpect(jsonPath("$.nationality").value("Nationality B"))
                .andExpect(jsonPath("$.birthDate").isNotEmpty()).andReturn();
    }

    @Test
    void updateCoach() throws Exception {
        final String NAME = "Coach D";
        final String NATIONALITY = "Nationality D";

        CoachDTO coachDTO = new CoachDTO(NAME, NATIONALITY, LocalDate.now().minusYears(1).toString(), "");
        String body = new ObjectMapper().writeValueAsString(coachDTO);

        MvcResult mvcResult = mockMvc.perform(put(COACH + "/{id}", uuidA).content(body)
                        .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.nationality").value(NATIONALITY)).andReturn();
    }

    @Test
    void deleteCoach() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete(COACH + "/{id}", uuidA)).andDo(print()).andExpect(status().isOk())
                .andReturn();
    }
}