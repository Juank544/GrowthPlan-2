package co.com.perficient.project3.service;

import co.com.perficient.project3.model.entity.Match;

import java.util.List;
import java.util.UUID;

public interface MatchService extends CrudService<Match, UUID> {
    List<Match> findLast3Matches();
}
