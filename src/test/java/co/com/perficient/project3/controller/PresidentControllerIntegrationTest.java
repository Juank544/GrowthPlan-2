package co.com.perficient.project3.controller;

import co.com.perficient.project3.model.dto.PresidentDTO;
import co.com.perficient.project3.model.entity.President;
import co.com.perficient.project3.repository.PresidentRepository;
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
import java.util.UUID;

import static co.com.perficient.project3.utils.constant.PresidentConstants.PRESIDENT_ENDPOINT;
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
class PresidentControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PresidentRepository presidentRepository;

    private final UUID uuidA = UUID.fromString("1852622d-e698-41e9-b682-cf04a5ccf280");
    private final UUID uuidB = UUID.fromString("f09be61e-682b-46f5-8905-0cd71151063e");

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        President presidentA = President.builder().id(uuidA).name("President A").nationality("Nationality A")
                .birthDate(LocalDate.now().minusYears(1)).build();
        President presidentB = President.builder().id(uuidB).name("President B").nationality("Nationality B")
                .birthDate(LocalDate.now().minusYears(1)).build();
        presidentRepository.saveAll(Arrays.asList(presidentA, presidentB));
    }

    @Test
    void createPresident() throws Exception {
        final String NAME = "President C";
        final String NATIONALITY = "Nationality C";

        PresidentDTO presidentDTO = new PresidentDTO();
        presidentDTO.setName(NAME);
        presidentDTO.setNationality(NATIONALITY);
        presidentDTO.setBirthDate(LocalDate.now().minusYears(1).toString());
        presidentDTO.setTeam("");
        String body = new ObjectMapper().writeValueAsString(presidentDTO);

        MvcResult mvcResult = mockMvc.perform(post(PRESIDENT_ENDPOINT).content(body)
                        .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.nationality").value(NATIONALITY)).andExpect(jsonPath("$.birthDate").isNotEmpty())
                .andReturn();
    }

    @Test
    void findAllPresidents() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(PRESIDENT_ENDPOINT)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.size()").value(2)).andReturn();
    }

    @Test
    void findPresidentById() throws Exception {
        mockMvc.perform(get(PRESIDENT_ENDPOINT + "/{id}", uuidB)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("President B"))
                .andExpect(jsonPath("$.nationality").value("Nationality B"))
                .andExpect(jsonPath("$.birthDate").isNotEmpty()).andReturn();
    }

    @Test
    void updatePresident() throws Exception {
        final String NAME = "President D";
        final String NATIONALITY = "Nationality D";

        PresidentDTO presidentDTO = new PresidentDTO();
        presidentDTO.setName(NAME);
        presidentDTO.setNationality(NATIONALITY);
        presidentDTO.setBirthDate(LocalDate.now().minusYears(1).toString());
        presidentDTO.setTeam("");
        String body = new ObjectMapper().writeValueAsString(presidentDTO);

        MvcResult mvcResult = mockMvc.perform(put(PRESIDENT_ENDPOINT + "/{id}", uuidA).content(body)
                        .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.nationality").value(NATIONALITY)).andReturn();
    }

    @Test
    void deletePresident() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete(PRESIDENT_ENDPOINT + "/{id}", uuidA)).andDo(print())
                .andExpect(status().isOk()).andReturn();
    }
}