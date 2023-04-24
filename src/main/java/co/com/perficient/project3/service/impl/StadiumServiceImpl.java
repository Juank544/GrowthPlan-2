package co.com.perficient.project3.service.impl;

import co.com.perficient.project3.model.entity.Stadium;
import co.com.perficient.project3.repository.StadiumRepository;
import co.com.perficient.project3.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class StadiumServiceImpl implements StadiumService {

    @Autowired
    private StadiumRepository stadiumRepository;

    @Override
    public Stadium create(Stadium stadium) {
        return stadiumRepository.save(stadium);
    }

    @Override
    public List<Stadium> findAll() {
        return stadiumRepository.findAll();
    }

    @Override
    public Optional<Stadium> findById(String id) {
        return stadiumRepository.findById(id);
    }

    @Override
    public Stadium update(Stadium oldStadium, Stadium newStadium) {
        oldStadium.setName(newStadium.getName());
        oldStadium.setCountry(newStadium.getCountry());
        oldStadium.setCity(newStadium.getCity());
        oldStadium.setCapacity(newStadium.getCapacity());
        return stadiumRepository.saveAndFlush(oldStadium);
    }

    @Override
    public void delete(String id) {
        stadiumRepository.deleteById(id);
    }

    @Override
    public Stadium partialUpdate(Stadium stadium, Map<String, Object> fields) {
        fields.forEach((s, value) -> {
            Field field = ReflectionUtils.findField(Stadium.class, s);
            if (Objects.nonNull(field)) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, stadium, value);
            }
        });
        return stadiumRepository.saveAndFlush(stadium);
    }
}
