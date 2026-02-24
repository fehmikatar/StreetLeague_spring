package tn.esprit._4se2.pi.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit._4se2.pi.entities.TeamMember;
import tn.esprit._4se2.pi.entities.TeamMemberId;
import tn.esprit._4se2.pi.repository.TeamMemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamMemberService implements ITeamMemberService {

    private final TeamMemberRepository teamMemberRepository;

    @Override
    public TeamMember addTeamMember(TeamMember teamMember) {
        return teamMemberRepository.save(teamMember);
    }

    @Override
    public List<TeamMember> getAllTeamMembers() {
        return teamMemberRepository.findAll();
    }

    @Override
    public TeamMember getTeamMemberById(TeamMemberId id) {
        return teamMemberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "TeamMember not found with userId: " + id.getUserId() + " and teamId: " + id.getTeamId()));
    }

    @Override
    public TeamMember updateTeamMember(TeamMember teamMember) {
        if (!teamMemberRepository.existsById(teamMember.getId())) {
            throw new RuntimeException("TeamMember not found");
        }
        return teamMemberRepository.save(teamMember);
    }

    @Override
    public void deleteTeamMember(TeamMemberId id) {
        teamMemberRepository.deleteById(id);
    }
}
