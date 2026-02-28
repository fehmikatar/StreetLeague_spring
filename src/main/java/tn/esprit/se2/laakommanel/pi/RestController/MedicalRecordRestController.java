package tn.esprit.se2.laakommanel.pi.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import tn.esprit.se2.laakommanel.pi.entites.InjuryType;
import tn.esprit.se2.laakommanel.pi.entites.MedicalRecord;
import tn.esprit.se2.laakommanel.pi.entites.RecoveryStatus;
import tn.esprit.se2.laakommanel.pi.entites.HealthProfile;
import tn.esprit.se2.laakommanel.pi.entites.User;
import tn.esprit.se2.laakommanel.pi.dto.MedicalRecordRequest;
import tn.esprit.se2.laakommanel.pi.dto.MedicalRecordResponse;
import tn.esprit.se2.laakommanel.pi.services.IMedicalRecordService;
import tn.esprit.se2.laakommanel.pi.services.IHealthProfileService;
import tn.esprit.se2.laakommanel.pi.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/medical-records")
@RequiredArgsConstructor
@Tag(name = "Medical Records", description = "📋 Medical history and diagnoses")
public class MedicalRecordRestController {

    private final IMedicalRecordService medicalRecordService;
    private final IHealthProfileService healthProfileService;
    private final IUserService userService;

    @GetMapping
    public ResponseEntity<List<MedicalRecordResponse>> getAllMedicalRecords() {
        List<MedicalRecord> records = medicalRecordService.getAllMedicalRecords();
        List<MedicalRecordResponse> responses = records.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecordResponse> getMedicalRecordById(@PathVariable Long id) {
        MedicalRecord record = medicalRecordService.getMedicalRecordById(id);
        MedicalRecordResponse response = convertToResponse(record);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<MedicalRecordResponse> createMedicalRecord(@RequestBody MedicalRecordRequest request) {
        MedicalRecord record = convertToEntity(request);
        MedicalRecord created = medicalRecordService.createMedicalRecord(record);
        MedicalRecordResponse response = convertToResponse(created);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecordResponse> updateMedicalRecord(@PathVariable Long id, @RequestBody MedicalRecordRequest request) {
        MedicalRecord record = convertToEntity(request);
        MedicalRecord updated = medicalRecordService.updateMedicalRecord(id, record);
        MedicalRecordResponse response = convertToResponse(updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalRecord(@PathVariable Long id) {
        medicalRecordService.deleteMedicalRecord(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/health-profile/{healthProfileId}")
    public ResponseEntity<MedicalRecordResponse> getMedicalRecordByHealthProfile(@PathVariable Long healthProfileId) {
        MedicalRecord record = medicalRecordService.getMedicalRecordByHealthProfile(healthProfileId);
        MedicalRecordResponse response = convertToResponse(record);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/injury-type/{injuryType}")
    public ResponseEntity<List<MedicalRecordResponse>> getByInjuryType(@PathVariable InjuryType injuryType) {
        List<MedicalRecord> records = medicalRecordService.getMedicalRecordsByInjuryType(injuryType);
        List<MedicalRecordResponse> responses = records.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/recovery-status/{status}")
    public ResponseEntity<List<MedicalRecordResponse>> getByRecoveryStatus(@PathVariable RecoveryStatus status) {
        List<MedicalRecord> records = medicalRecordService.getMedicalRecordsByRecoveryStatus(status);
        List<MedicalRecordResponse> responses = records.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<MedicalRecordResponse>> getByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        List<MedicalRecord> records = medicalRecordService.getMedicalRecordsByDateRange(start, end);
        List<MedicalRecordResponse> responses = records.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/health-profile/{healthProfileId}/injury-type/{injuryType}")
    public ResponseEntity<MedicalRecordResponse> getByHealthProfileAndInjuryType(
            @PathVariable Long healthProfileId,
            @PathVariable InjuryType injuryType) {
        MedicalRecord record = medicalRecordService.getMedicalRecordByHealthProfileAndInjuryType(healthProfileId, injuryType);
        MedicalRecordResponse response = convertToResponse(record);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<List<MedicalRecordResponse>> searchByDiagnosis(@RequestParam String keyword) {
        List<MedicalRecord> records = medicalRecordService.searchMedicalRecordsByDiagnosis(keyword);
        List<MedicalRecordResponse> responses = records.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<MedicalRecordResponse>> getByDoctor(@PathVariable Long doctorId) {
        List<MedicalRecord> records = medicalRecordService.getMedicalRecordsByDoctor(doctorId);
        List<MedicalRecordResponse> responses = records.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/requires-followup")
    public ResponseEntity<List<MedicalRecordResponse>> getRequiringFollowUp() {
        List<MedicalRecord> records = medicalRecordService.getMedicalRecordsRequiringFollowUp();
        List<MedicalRecordResponse> responses = records.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/active")
    public ResponseEntity<List<MedicalRecordResponse>> getActiveRecords() {
        List<MedicalRecord> records = medicalRecordService.getActiveMedicalRecords();
        List<MedicalRecordResponse> responses = records.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    // =============== Méthodes de conversion ===============

    private MedicalRecord convertToEntity(MedicalRecordRequest request) {
        if (request == null) return null;

        MedicalRecord record = new MedicalRecord();
        record.setDiagnosis(request.getDiagnosis());
        record.setInjuryDate(request.getInjuryDate());
        record.setExpectedRecoveryDate(request.getExpectedRecoveryDate());
        record.setDoctorNotes(request.getDoctorNotes());
        record.setInjuryType(request.getInjuryType());
        record.setRecoveryStatus(request.getRecoveryStatus());
        record.setMedicalCertificateUrl(request.getMedicalCertificateUrl());
        record.setTreatment(request.getTreatment());
        record.setMedication(request.getMedication());
        record.setRequiresFollowUp(request.getRequiresFollowUp());


        if (request.getTreatedByDoctorId() != null) {
            User doctor = userService.getUserById(request.getTreatedByDoctorId());
            record.setTreatedBy(doctor);
        }

        return record;
    }

    private MedicalRecordResponse convertToResponse(MedicalRecord record) {
        if (record == null) return null;

        return MedicalRecordResponse.builder()
                .id(record.getId())
                .healthProfile(record.getHealthProfile())
                .diagnosis(record.getDiagnosis())
                .injuryDate(record.getInjuryDate())
                .expectedRecoveryDate(record.getExpectedRecoveryDate())
                .actualRecoveryDate(record.getActualRecoveryDate())
                .doctorNotes(record.getDoctorNotes())
                .injuryType(record.getInjuryType())
                .recoveryStatus(record.getRecoveryStatus())
                .medicalCertificateUrl(record.getMedicalCertificateUrl())
                .treatment(record.getTreatment())
                .medication(record.getMedication())
                .requiresFollowUp(record.getRequiresFollowUp())
                .treatedBy(record.getTreatedBy())
                .createdAt(record.getCreatedAt())
                .updatedAt(record.getUpdatedAt())
                .build();
    }
}