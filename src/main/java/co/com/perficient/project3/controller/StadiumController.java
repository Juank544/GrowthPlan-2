package co.com.perficient.project3.controller;

import co.com.perficient.project3.mapper.StadiumMapper;
import co.com.perficient.project3.model.dto.StadiumDTO;
import co.com.perficient.project3.model.entity.Stadium;
import co.com.perficient.project3.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/stadium/")
public class StadiumController {

    @Autowired
    private StadiumService stadiumService;

    @Autowired
    private StadiumMapper stadiumMapper;

    @PostMapping
    public ResponseEntity<StadiumDTO> createStadium(@RequestBody StadiumDTO stadiumDTO) {
        Stadium stadium = stadiumService.create(stadiumMapper.toEntity(stadiumDTO));
        return new ResponseEntity<>(stadiumMapper.toDTO(stadium), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StadiumDTO>> findAllStadiums() {
        List<StadiumDTO> stadiums = stadiumService.findAll().stream().map(stadiumMapper::toDTO).toList();
        return new ResponseEntity<>(stadiums, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<StadiumDTO> findStadiumById(@PathVariable String id) {
        Optional<Stadium> optionalStadium = stadiumService.findById(id);
        return optionalStadium.map(stadium -> new ResponseEntity<>(stadiumMapper.toDTO(stadium), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @PutMapping("{id}")
    public ResponseEntity<StadiumDTO> updateStadium(@PathVariable String id, @RequestBody StadiumDTO stadiumDTO) {
        Optional<Stadium> optionalStadium = stadiumService.findById(id);
        if (optionalStadium.isPresent()) {
            Stadium stadium = stadiumService.update(optionalStadium.get(), stadiumMapper.toEntity(stadiumDTO));
            return new ResponseEntity<>(stadiumMapper.toDTO(stadium), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<StadiumDTO> deleteStadium(@PathVariable String id) {
        stadiumService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping({"{id}"})
    public ResponseEntity<StadiumDTO> patchStadium(@PathVariable String id, @RequestBody Map<String, Object> fields) {
        Optional<Stadium> optionalStadium = stadiumService.findById(id);
        if (optionalStadium.isPresent()) {
            Stadium stadium = stadiumService.partialUpdate(optionalStadium.get(), fields);
            return new ResponseEntity<>(stadiumMapper.toDTO(stadium), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
