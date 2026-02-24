package tn.esprit._4se2.pi.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit._4se2.pi.entities.Team;
import tn.esprit._4se2.pi.repository.TeamRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService implements ITeamService {

    private final TeamRepository teamRepository;

    @Override
    public Team addTeam(Team team) {
        team.setCreatedAt(LocalDate.now());
        return teamRepository.save(team);
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team getTeamById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found with id: " + id));
    }

    @Override
    public Team updateTeam(Team team) {
        if (!teamRepository.existsById(team.getId())) {
            throw new RuntimeException("Team not found with id: " + team.getId());
        }
        return teamRepository.save(team);
    }

    @Override
    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }
}
