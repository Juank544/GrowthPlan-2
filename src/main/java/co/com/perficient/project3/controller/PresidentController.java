package co.com.perficient.project3.controller;

import co.com.perficient.project3.mapper.PresidentMapper;
import co.com.perficient.project3.model.dto.PresidentDTO;
import co.com.perficient.project3.model.dto.TeamDTO;
import co.com.perficient.project3.model.entity.President;
import co.com.perficient.project3.service.PresidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static co.com.perficient.project3.utils.constant.PresidentConstants.PRESIDENT_ENDPOINT;

@RestController
@RequestMapping(PRESIDENT_ENDPOINT)
public class PresidentController {

    @Autowired
    private PresidentService presidentService;
    @Autowired
    private PresidentMapper presidentMapper;

    @PostMapping
    public ResponseEntity<PresidentDTO> createPresident(@RequestBody PresidentDTO presidentDTO) {
        President president = presidentService.create(presidentMapper.toEntity(presidentDTO));
        return new ResponseEntity<>(presidentMapper.toDTO(president), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PresidentDTO>> findAllPresidents() {
        List<PresidentDTO> presidents = presidentService.findAll().stream().map(presidentMapper::toDTO).toList();
        return new ResponseEntity<>(presidents, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PresidentDTO> findPresidentById(@PathVariable UUID id) {
        Optional<President> optionalPresident = presidentService.findById(id);
        return optionalPresident.map(president -> new ResponseEntity<>(presidentMapper.toDTO(president), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PresidentDTO> updatePresident(@PathVariable UUID id, @RequestBody PresidentDTO presidentDTO) {
        Optional<President> optionalPresident = presidentService.findById(id);
        if (optionalPresident.isPresent()) {
            President president = presidentService.update(optionalPresident.get(), presidentMapper.toEntity(presidentDTO));
            return new ResponseEntity<>(presidentMapper.toDTO(president), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TeamDTO> deletePresident(@PathVariable UUID id) {
        presidentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}