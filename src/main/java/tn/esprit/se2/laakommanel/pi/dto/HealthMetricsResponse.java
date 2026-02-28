package tn.esprit.se2.laakommanel.pi.dto;

import lombok.*;
import tn.esprit.se2.laakommanel.pi.entites.HealthProfile;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthMetricsResponse {
    private Long id;
    private HealthProfile healthProfile;
    private Double weight;
    private Double muscleMass;
    private Double bodyFat;
    private Double hydration;
    private Integer restingHeartRate;
    private Integer systolicBP;
    private Integer diastolicBP;
    private Integer sleepHours;
    private Integer stressLevel;
    private Integer energyLevel;
    private LocalDateTime measuredAt;
    private String notes;
}