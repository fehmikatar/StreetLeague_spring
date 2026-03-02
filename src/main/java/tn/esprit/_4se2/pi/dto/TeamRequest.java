package tn.esprit._4se2.pi.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data

public class TeamRequest {
    @NotBlank(message = "Le nom de l'équipe est obligatoire")
    private String name;

    private String description;
}
