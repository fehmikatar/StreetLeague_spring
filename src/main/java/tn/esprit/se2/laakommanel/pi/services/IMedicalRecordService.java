package tn.esprit.se2.laakommanel.pi.services;

import tn.esprit.se2.laakommanel.pi.entites.MedicalRecord;
import tn.esprit.se2.laakommanel.pi.entites.InjuryType;
import tn.esprit.se2.laakommanel.pi.entites.RecoveryStatus;
import java.time.LocalDate;
import java.util.List;

public interface IMedicalRecordService {
    // CRUD de base
    List<MedicalRecord> getAllMedicalRecords();
    MedicalRecord getMedicalRecordById(Long id);
    MedicalRecord createMedicalRecord(MedicalRecord medicalRecord);
    MedicalRecord updateMedicalRecord(Long id, MedicalRecord medicalRecord);
    void deleteMedicalRecord(Long id);

    // Recherches spécifiques - CORRIGÉ
    MedicalRecord getMedicalRecordByHealthProfile(Long healthProfileId);
    List<MedicalRecord> getMedicalRecordsByInjuryType(InjuryType injuryType);
    List<MedicalRecord> getMedicalRecordsByRecoveryStatus(RecoveryStatus status);
    List<MedicalRecord> getMedicalRecordsByDateRange(LocalDate startDate, LocalDate endDate);
    MedicalRecord getMedicalRecordByHealthProfileAndInjuryType(Long healthProfileId, InjuryType injuryType);
    List<MedicalRecord> searchMedicalRecordsByDiagnosis(String keyword);
    List<MedicalRecord> getMedicalRecordsByDoctor(Long doctorId);
    List<MedicalRecord> getMedicalRecordsRequiringFollowUp();
    List<MedicalRecord> getActiveMedicalRecords();
    Double getAverageRecoveryTimeByInjuryType(InjuryType injuryType);
}