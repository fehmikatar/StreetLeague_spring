package tn.esprit.se2.laakommanel.pi.dto;

import lombok.*;
import tn.esprit.se2.laakommanel.pi.entites.FitnessStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthProfileRequest {
    private Double weight;
    private Double height;
    private Integer age;
    private String sportPosition;
    private FitnessStatus fitnessStatus;
    private String emergencyContact;
    private String emergencyPhone;
    private String bloodType;
    private String allergies;
    private String medicalConditions;
}