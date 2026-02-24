package tn.esprit._4se2.pi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit._4se2.pi.entities.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
