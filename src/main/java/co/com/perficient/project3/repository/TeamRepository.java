package co.com.perficient.project3.repository;

import co.com.perficient.project3.model.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeamRepository extends JpaRepository<Team, UUID>, QuerydslPredicateExecutor<Team> {
}
