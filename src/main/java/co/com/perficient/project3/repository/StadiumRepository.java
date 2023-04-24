package co.com.perficient.project3.repository;

import co.com.perficient.project3.model.entity.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StadiumRepository extends JpaRepository<Stadium, String> {
}
