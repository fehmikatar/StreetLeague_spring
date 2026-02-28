package tn.esprit.se2.laakommanel.pi.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import tn.esprit.se2.laakommanel.pi.entites.FitnessStatus;
import tn.esprit.se2.laakommanel.pi.entites.HealthProfile;
import tn.esprit.se2.laakommanel.pi.entites.User;
import tn.esprit.se2.laakommanel.pi.dto.HealthProfileRequest;
import tn.esprit.se2.laakommanel.pi.dto.HealthProfileResponse;
import tn.esprit.se2.laakommanel.pi.services.IHealthProfileService;
import tn.esprit.se2.laakommanel.pi.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/health-profiles")
@RequiredArgsConstructor
@Tag(name = "Health Profiles", description = "📊 Patient health profiles")
public class HealthProfileRestController {

    private final IHealthProfileService healthProfileService;
    private final IUserService userService;

    @GetMapping
    public ResponseEntity<List<HealthProfileResponse>> getAllHealthProfiles() {
        List<HealthProfile> profiles = healthProfileService.getAllHealthProfiles();
        List<HealthProfileResponse> responses = profiles.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HealthProfileResponse> getHealthProfileById(@PathVariable Long id) {
        HealthProfile profile = healthProfileService.getHealthProfileById(id);
        HealthProfileResponse response = convertToResponse(profile);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<HealthProfileResponse> createHealthProfile(@RequestBody HealthProfileRequest request) {
        HealthProfile profile = convertToEntity(request);
        HealthProfile created = healthProfileService.createHealthProfile(profile);
        HealthProfileResponse response = convertToResponse(created);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HealthProfileResponse> updateHealthProfile(@PathVariable Long id, @RequestBody HealthProfileRequest request) {
        HealthProfile profile = convertToEntity(request);
        HealthProfile updated = healthProfileService.updateHealthProfile(id, profile);
        HealthProfileResponse response = convertToResponse(updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHealthProfile(@PathVariable Long id) {
        healthProfileService.deleteHealthProfile(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<HealthProfileResponse> getHealthProfileByUser(@PathVariable Long userId) {
        HealthProfile profile = healthProfileService.getHealthProfileByUser(userId);
        HealthProfileResponse response = convertToResponse(profile);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/fitness-status/{status}")
    public ResponseEntity<List<HealthProfileResponse>> getByFitnessStatus(@PathVariable FitnessStatus status) {
        List<HealthProfile> profiles = healthProfileService.getHealthProfilesByFitnessStatus(status);
        List<HealthProfileResponse> responses = profiles.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    // =============== Méthodes de conversion ===============

    private HealthProfile convertToEntity(HealthProfileRequest request) {
        if (request == null) return null;

        HealthProfile profile = new HealthProfile();
        profile.setWeight(request.getWeight());
        profile.setHeight(request.getHeight());
        profile.setAge(request.getAge());
        profile.setSportPosition(request.getSportPosition());
        profile.setFitnessStatus(request.getFitnessStatus());
        profile.setEmergencyContact(request.getEmergencyContact());
        profile.setEmergencyPhone(request.getEmergencyPhone());
        profile.setBloodType(request.getBloodType());
        profile.setAllergies(request.getAllergies());
        profile.setMedicalConditions(request.getMedicalConditions());

        return profile;
    }

    private HealthProfileResponse convertToResponse(HealthProfile profile) {
        if (profile == null) return null;

        return HealthProfileResponse.builder()
                .id(profile.getId())
                .user(profile.getUser())
                .weight(profile.getWeight())
                .height(profile.getHeight())
                .age(profile.getAge())
                .sportPosition(profile.getSportPosition())
                .fitnessStatus(profile.getFitnessStatus())
                .lastUpdated(profile.getLastUpdated())
                .emergencyContact(profile.getEmergencyContact())
                .emergencyPhone(profile.getEmergencyPhone())
                .bloodType(profile.getBloodType())
                .allergies(profile.getAllergies())
                .medicalConditions(profile.getMedicalConditions())
                .medicalRecords(profile.getMedicalRecords())
                .healthMetrics(profile.getHealthMetrics())
                .dietPlans(profile.getDietPlans())
                .build();
    }
}