package tn.esprit.se2.laakommanel.pi.dto;

import lombok.*;
import tn.esprit.se2.laakommanel.pi.entites.HealthProfile;
import tn.esprit.se2.laakommanel.pi.entites.InjuryType;
import tn.esprit.se2.laakommanel.pi.entites.RecoveryStatus;
import tn.esprit.se2.laakommanel.pi.entites.User;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRecordResponse {
    private Long id;
    private HealthProfile healthProfile;
    private String diagnosis;
    private LocalDate injuryDate;
    private LocalDate expectedRecoveryDate;
    private LocalDate actualRecoveryDate;
    private String doctorNotes;
    private InjuryType injuryType;
    private RecoveryStatus recoveryStatus;
    private String medicalCertificateUrl;
    private String treatment;
    private String medication;
    private Boolean requiresFollowUp;
    private User treatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}