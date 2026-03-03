package tn.esprit._4se2.laakommanel.pi.dto;

import lombok.*;
import tn.esprit._4se2.laakommanel.pi.entites.InjuryType;
import tn.esprit._4se2.laakommanel.pi.entites.RecoveryStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRecordResponse {
    private Long id;
    private Long healthProfileId;
    private Long treatedByDoctorId;
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}