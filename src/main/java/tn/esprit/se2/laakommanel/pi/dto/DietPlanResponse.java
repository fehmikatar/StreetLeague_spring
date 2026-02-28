package tn.esprit.se2.laakommanel.pi.dto;

import lombok.*;
import tn.esprit.se2.laakommanel.pi.entites.HealthProfile;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DietPlanResponse {
    private Long id;
    private HealthProfile healthProfile;
    private String planName;
    private String description;
    private Integer dailyCalories;
    private String mealSuggestions;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;
    private String dietaryRestrictions;
    private String nutritionalGoals;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}