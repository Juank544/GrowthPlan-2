package co.com.perficient.project3.controller;

import co.com.perficient.project3.model.dto.StadiumDTO;
import co.com.perficient.project3.model.entity.Stadium;
import co.com.perficient.project3.repository.StadiumRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class StadiumControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private StadiumRepository stadiumRepository;

    private final UUID uuidA = UUID.fromString("1852622d-e698-41e9-b682-cf04a5ccf280");
    private final UUID uuidB = UUID.fromString("f09be61e-682b-46f5-8905-0cd71151063e");

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        Stadium stadiumSB = Stadium.builder().id(uuidA).name("Stadium A").country("Country A").city("City A")
                .capacity("1").build();
        Stadium stadiumPP = Stadium.builder().id(uuidB).name("Stadium B").country("Country B").city("City B")
                .capacity("2").build();
        stadiumRepository.saveAll(Arrays.asList(stadiumSB, stadiumPP));
    }

    @AfterEach
    void cleanData() {
        stadiumRepository.deleteAllById(Arrays.asList(uuidA, uuidB));
    }

    @Test
    void createStadium() throws Exception {
        final String NAME = "Stadium C";
        final String COUNTRY = "Country C";
        final String CITY = "City C";
        final String CAPACITY = "3";

        StadiumDTO stadiumDTO = new StadiumDTO();
        stadiumDTO.setName(NAME);
        stadiumDTO.setCountry(COUNTRY);
        stadiumDTO.setCity(CITY);
        stadiumDTO.setCapacity(CAPACITY);
        String body = new ObjectMapper().writeValueAsString(stadiumDTO);

        MvcResult mvcResult = mockMvc.perform(post("/api/stadium/").content(body)
                        .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(NAME)).andExpect(jsonPath("$.country").value(COUNTRY))
                .andExpect(jsonPath("$.city").value(CITY)).andExpect(jsonPath("$.capacity").value(CAPACITY))
                .andReturn();
    }

    @Test
    void findAllStadiums() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/stadium/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.size()").value(2))
                .andReturn();
    }

    @Test
    void findStadiumById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/stadium/{id}", uuidB)).andDo(print())
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.city").value("City B")).andReturn();
    }

    @Test
    void updateStadium() throws Exception {
        final String NAME = "Stadium D";
        final String COUNTRY = "Country D";
        final String CITY = "City D";
        final String CAPACITY = "4";

        StadiumDTO stadiumDTO = new StadiumDTO();
        stadiumDTO.setName(NAME);
        stadiumDTO.setCountry(COUNTRY);
        stadiumDTO.setCity(CITY);
        stadiumDTO.setCapacity(CAPACITY);
        String body = new ObjectMapper().writeValueAsString(stadiumDTO);

        MvcResult mvcResult = mockMvc.perform(put("/api/stadium/{id}", uuidA).content(body)
                        .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(NAME)).andExpect(jsonPath("$.country").value(COUNTRY))
                .andExpect(jsonPath("$.city").value(CITY)).andExpect(jsonPath("$.capacity").value(CAPACITY))
                .andReturn();
    }

    @Test
    void deleteStadium() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/api/stadium/{id}", uuidA)).andDo(print())
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void patchStadium() throws Exception {
        final String NAME = "Stadium E";
        final String CAPACITY = "5";

        StadiumDTO stadiumDTO = new StadiumDTO();
        stadiumDTO.setName(NAME);
        stadiumDTO.setCapacity(CAPACITY);
        String body = new ObjectMapper().writeValueAsString(stadiumDTO);

        MvcResult mvcResult = mockMvc.perform(patch("/api/stadium/{id}", uuidB).content(body)
                        .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(NAME)).andExpect(jsonPath("$.country").value("Country B"))
                .andExpect(jsonPath("$.city").value("City B")).andExpect(jsonPath("$.capacity").value(CAPACITY))
                .andReturn();
    }
}