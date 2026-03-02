package tn.esprit._4se2.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit._4se2.pi.entities.TeamMember;
import tn.esprit._4se2.pi.entities.TeamMemberId;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, TeamMemberId> {
}
