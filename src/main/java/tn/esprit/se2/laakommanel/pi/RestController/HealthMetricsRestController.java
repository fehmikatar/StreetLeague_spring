package tn.esprit.se2.laakommanel.pi.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import tn.esprit.se2.laakommanel.pi.entites.HealthMetrics;
import tn.esprit.se2.laakommanel.pi.entites.HealthProfile;
import tn.esprit.se2.laakommanel.pi.dto.HealthMetricsRequest;
import tn.esprit.se2.laakommanel.pi.dto.HealthMetricsResponse;
import tn.esprit.se2.laakommanel.pi.services.IHealthMetricsService;
import tn.esprit.se2.laakommanel.pi.services.IHealthProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/health-metrics")
@Tag(name = "Health Metrics", description = "📈 Vital signs and fitness metrics")
@RequiredArgsConstructor
public class HealthMetricsRestController {

    private final IHealthMetricsService healthMetricsService;
    private final IHealthProfileService healthProfileService;

    @GetMapping
    public ResponseEntity<List<HealthMetricsResponse>> getAllHealthMetrics() {
        List<HealthMetrics> metrics = healthMetricsService.getAllHealthMetrics();
        List<HealthMetricsResponse> responses = metrics.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HealthMetricsResponse> getHealthMetricsById(@PathVariable Long id) {
        HealthMetrics metrics = healthMetricsService.getHealthMetricsById(id);
        HealthMetricsResponse response = convertToResponse(metrics);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<HealthMetricsResponse> createHealthMetrics(@RequestBody HealthMetricsRequest request) {
        HealthMetrics metrics = convertToEntity(request);
        HealthMetrics created = healthMetricsService.createHealthMetrics(metrics);
        HealthMetricsResponse response = convertToResponse(created);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HealthMetricsResponse> updateHealthMetrics(@PathVariable Long id, @RequestBody HealthMetricsRequest request) {
        HealthMetrics metrics = convertToEntity(request);
        HealthMetrics updated = healthMetricsService.updateHealthMetrics(id, metrics);
        HealthMetricsResponse response = convertToResponse(updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHealthMetrics(@PathVariable Long id) {
        healthMetricsService.deleteHealthMetrics(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/health-profile/{healthProfileId}")
    public ResponseEntity<List<HealthMetricsResponse>> getHealthMetricsByHealthProfile(@PathVariable Long healthProfileId) {
        List<HealthMetrics> metrics = healthMetricsService.getHealthMetricsByHealthProfile(healthProfileId);
        List<HealthMetricsResponse> responses = metrics.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/health-profile/{healthProfileId}/latest")
    public ResponseEntity<HealthMetricsResponse> getLatestHealthMetrics(@PathVariable Long healthProfileId) {
        HealthMetrics metrics = healthMetricsService.getLatestHealthMetricsByHealthProfile(healthProfileId);
        HealthMetricsResponse response = convertToResponse(metrics);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health-profile/{healthProfileId}/date-range")
    public ResponseEntity<List<HealthMetricsResponse>> getHealthMetricsByDateRange(
            @PathVariable Long healthProfileId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<HealthMetrics> metrics = healthMetricsService.getHealthMetricsByDateRange(healthProfileId, startDate, endDate);
        List<HealthMetricsResponse> responses = metrics.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/date")
    public ResponseEntity<List<HealthMetricsResponse>> getHealthMetricsByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        List<HealthMetrics> metrics = healthMetricsService.getHealthMetricsByDate(date);
        List<HealthMetricsResponse> responses = metrics.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/heart-rate/range")
    public ResponseEntity<List<HealthMetricsResponse>> getHealthMetricsByHeartRateRange(
            @RequestParam int min,
            @RequestParam int max) {
        List<HealthMetrics> metrics = healthMetricsService.getHealthMetricsByHeartRateRange(min, max);
        List<HealthMetricsResponse> responses = metrics.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/blood-pressure/min")
    public ResponseEntity<List<HealthMetricsResponse>> getHealthMetricsByBloodPressureMin(
            @RequestParam int systolic,
            @RequestParam int diastolic) {
        List<HealthMetrics> metrics = healthMetricsService.getHealthMetricsByBloodPressureMin(systolic, diastolic);
        List<HealthMetricsResponse> responses = metrics.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/abnormal-heart-rate")
    public ResponseEntity<List<HealthMetricsResponse>> getAbnormalHeartRateMetrics() {
        List<HealthMetrics> metrics = healthMetricsService.getAbnormalHeartRateMetrics();
        List<HealthMetricsResponse> responses = metrics.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    // =============== Méthodes de conversion ===============

    private HealthMetrics convertToEntity(HealthMetricsRequest request) {
        if (request == null) return null;

        HealthMetrics metrics = new HealthMetrics();
        metrics.setWeight(request.getWeight());
        metrics.setMuscleMass(request.getMuscleMass());
        metrics.setBodyFat(request.getBodyFat());
        metrics.setHydration(request.getHydration());
        metrics.setRestingHeartRate(request.getRestingHeartRate());
        metrics.setSystolicBP(request.getSystolicBP());
        metrics.setDiastolicBP(request.getDiastolicBP());
        metrics.setSleepHours(request.getSleepHours());
        metrics.setStressLevel(request.getStressLevel());
        metrics.setEnergyLevel(request.getEnergyLevel());
        metrics.setMeasuredAt(request.getMeasuredAt() != null ? request.getMeasuredAt() : LocalDateTime.now());
        metrics.setNotes(request.getNotes());


        return metrics;
    }

    private HealthMetricsResponse convertToResponse(HealthMetrics metrics) {
        if (metrics == null) return null;

        return HealthMetricsResponse.builder()
                .id(metrics.getId())
                .healthProfile(metrics.getHealthProfile())
                .weight(metrics.getWeight())
                .muscleMass(metrics.getMuscleMass())
                .bodyFat(metrics.getBodyFat())
                .hydration(metrics.getHydration())
                .restingHeartRate(metrics.getRestingHeartRate())
                .systolicBP(metrics.getSystolicBP())
                .diastolicBP(metrics.getDiastolicBP())
                .sleepHours(metrics.getSleepHours())
                .stressLevel(metrics.getStressLevel())
                .energyLevel(metrics.getEnergyLevel())
                .measuredAt(metrics.getMeasuredAt())
                .notes(metrics.getNotes())
                .build();
    }
}