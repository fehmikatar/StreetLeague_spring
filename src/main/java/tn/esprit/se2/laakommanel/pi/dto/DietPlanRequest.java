package tn.esprit.se2.laakommanel.pi.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DietPlanRequest {
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
}