package tn.esprit.se2.laakommanel.pi.dto;

import lombok.*;
import tn.esprit.se2.laakommanel.pi.entites.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthProfileResponse {
    private Long id;
    private User user;
    private Double weight;
    private Double height;
    private Integer age;
    private String sportPosition;
    private FitnessStatus fitnessStatus;
    private LocalDate lastUpdated;
    private String emergencyContact;
    private String emergencyPhone;
    private String bloodType;
    private String allergies;
    private String medicalConditions;
    private List<MedicalRecord> medicalRecords;
    private List<HealthMetrics> healthMetrics;
    private List<DietPlan> dietPlans;
}