package tn.esprit._4se2.pi.dto;

import lombok.Data;
@Data

public class TeamMemberResponse {
    private Long userId;
    private Long teamId;
    private String role;
}
