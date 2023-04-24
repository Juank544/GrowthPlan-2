package co.com.perficient.project3.service;

import co.com.perficient.project3.model.entity.Stadium;

import java.util.Map;

public interface StadiumService extends CrudService<Stadium, String> {

    Stadium partialUpdate(Stadium stadium, Map<String, Object> fields);
}
