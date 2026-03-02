package tn.esprit._4se2.pi.mapper;

import tn.esprit._4se2.pi.dto.TeamMemberRequest;
import tn.esprit._4se2.pi.dto.TeamMemberResponse;
import tn.esprit._4se2.pi.entities.Team;
import tn.esprit._4se2.pi.entities.TeamMember;
import tn.esprit._4se2.pi.entities.TeamMemberId;
import tn.esprit._4se2.pi.entities.User;

public class TeamMemberMapper {
    public static TeamMember toEntity(
            TeamMemberRequest dto,
            User user,
            Team team) {
        TeamMember teamMember = new TeamMember();
        teamMember.setId(new TeamMemberId(dto.getUserId(), dto.getTeamId()));
        teamMember.setUser(user);
        teamMember.setTeam(team);
        if (dto.getRole() != null) {
            teamMember.setRole(TeamMember.Role.valueOf(dto.getRole()));
        }
        return teamMember;
    }

    public static TeamMemberResponse toDto(TeamMember teamMember) {
        TeamMemberResponse dto = new TeamMemberResponse();
        dto.setUserId(teamMember.getId().getUserId());
        dto.setTeamId(teamMember.getId().getTeamId());
        dto.setRole(teamMember.getRole() != null ? teamMember.getRole().name() : null);
        return dto;
    }
}
