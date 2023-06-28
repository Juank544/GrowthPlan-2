package co.com.perficient.project3.repository;

import co.com.perficient.project3.model.entity.Standing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StandingRepository extends JpaRepository<Standing, UUID> {
}
