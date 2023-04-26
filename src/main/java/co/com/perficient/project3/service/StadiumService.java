package co.com.perficient.project3.service;

import co.com.perficient.project3.model.entity.Stadium;

import java.util.Map;
import java.util.UUID;

public interface StadiumService extends CrudService<Stadium, UUID> {

    Stadium patch(Stadium stadium, Map<String, Object> fields);
}
