package tn.esprit.se2.laakommanel.pi.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import tn.esprit.se2.laakommanel.pi.entites.DietPlan;
import tn.esprit.se2.laakommanel.pi.entites.HealthProfile;
import tn.esprit.se2.laakommanel.pi.dto.DietPlanRequest;
import tn.esprit.se2.laakommanel.pi.dto.DietPlanResponse;
import tn.esprit.se2.laakommanel.pi.services.IDietPlanService;
import tn.esprit.se2.laakommanel.pi.services.IHealthProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/diet-plans")
@RequiredArgsConstructor
@Tag(name = "Diet Plans", description = "🥗 Nutrition and diet plans")
public class DietPlanRestController {

    private final IDietPlanService dietPlanService;
    private final IHealthProfileService healthProfileService;

    @GetMapping
    public ResponseEntity<List<DietPlanResponse>> getAllDietPlans() {
        List<DietPlan> plans = dietPlanService.getAllDietPlans();
        List<DietPlanResponse> responses = plans.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DietPlanResponse> getDietPlanById(@PathVariable Long id) {
        DietPlan plan = dietPlanService.getDietPlanById(id);
        DietPlanResponse response = convertToResponse(plan);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<DietPlanResponse> createDietPlan(@RequestBody DietPlanRequest request) {
        DietPlan plan = convertToEntity(request);
        DietPlan created = dietPlanService.createDietPlan(plan);
        DietPlanResponse response = convertToResponse(created);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DietPlanResponse> updateDietPlan(@PathVariable Long id, @RequestBody DietPlanRequest request) {
        DietPlan plan = convertToEntity(request);
        DietPlan updated = dietPlanService.updateDietPlan(id, plan);
        DietPlanResponse response = convertToResponse(updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDietPlan(@PathVariable Long id) {
        dietPlanService.deleteDietPlan(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/health-profile/{healthProfileId}")
    public ResponseEntity<List<DietPlanResponse>> getDietPlansByHealthProfile(@PathVariable Long healthProfileId) {
        List<DietPlan> plans = dietPlanService.getDietPlansByHealthProfile(healthProfileId);
        List<DietPlanResponse> responses = plans.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/daily-calories/max/{maxCalories}")
    public ResponseEntity<List<DietPlanResponse>> getByMaxDailyCalories(@PathVariable int maxCalories) {
        List<DietPlan> plans = dietPlanService.getDietPlansByMaxDailyCalories(maxCalories);
        List<DietPlanResponse> responses = plans.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/daily-calories/range")
    public ResponseEntity<List<DietPlanResponse>> getByDailyCaloriesRange(
            @RequestParam int min,
            @RequestParam int max) {
        List<DietPlan> plans = dietPlanService.getDietPlansByDailyCaloriesRange(min, max);
        List<DietPlanResponse> responses = plans.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/health-profile/{healthProfileId}/active")
    public ResponseEntity<List<DietPlanResponse>> getActivePlansByHealthProfile(@PathVariable Long healthProfileId) {
        List<DietPlan> plans = dietPlanService.getActivePlansByHealthProfile(healthProfileId);
        List<DietPlanResponse> responses = plans.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/ending-soon")
    public ResponseEntity<List<DietPlanResponse>> getPlansEndingSoon(
            @RequestParam(required = false) LocalDate futureDate) {
        List<DietPlan> plans = dietPlanService.getPlansEndingSoon(futureDate);
        List<DietPlanResponse> responses = plans.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/search")
    public ResponseEntity<List<DietPlanResponse>> searchByPlanName(@RequestParam String name) {
        List<DietPlan> plans = dietPlanService.searchByPlanName(name);
        List<DietPlanResponse> responses = plans.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/health-profile/{healthProfileId}/average-calories")
    public ResponseEntity<Double> getAverageDailyCalories(@PathVariable Long healthProfileId) {
        Double average = dietPlanService.getAverageDailyCaloriesByHealthProfile(healthProfileId);
        return ResponseEntity.ok(average);
    }

    @GetMapping("/popular")
    public ResponseEntity<List<Object[]>> getMostPopularDietPlans() {
        List<Object[]> popular = dietPlanService.getMostPopularDietPlans();
        return ResponseEntity.ok(popular);
    }

    @PutMapping("/{id}/toggle-status")
    public ResponseEntity<DietPlanResponse> togglePlanStatus(@PathVariable Long id) {
        DietPlan plan = dietPlanService.togglePlanStatus(id);
        DietPlanResponse response = convertToResponse(plan);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/today-active")
    public ResponseEntity<List<DietPlanResponse>> getTodayActivePlans() {
        List<DietPlan> plans = dietPlanService.getTodayActivePlans();
        List<DietPlanResponse> responses = plans.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    // =============== Méthodes de conversion ===============

    private DietPlan convertToEntity(DietPlanRequest request) {
        if (request == null) return null;

        DietPlan plan = new DietPlan();
        plan.setPlanName(request.getPlanName());
        plan.setDescription(request.getDescription());
        plan.setDailyCalories(request.getDailyCalories());
        plan.setMealSuggestions(request.getMealSuggestions());
        plan.setStartDate(request.getStartDate());
        plan.setEndDate(request.getEndDate());
        plan.setIsActive(request.getIsActive());
        plan.setDietaryRestrictions(request.getDietaryRestrictions());
        plan.setNutritionalGoals(request.getNutritionalGoals());
        plan.setCreatedBy(request.getCreatedBy());


        return plan;
    }

    private DietPlanResponse convertToResponse(DietPlan plan) {
        if (plan == null) return null;

        return DietPlanResponse.builder()
                .id(plan.getId())
                .healthProfile(plan.getHealthProfile())
                .planName(plan.getPlanName())
                .description(plan.getDescription())
                .dailyCalories(plan.getDailyCalories())
                .mealSuggestions(plan.getMealSuggestions())
                .startDate(plan.getStartDate())
                .endDate(plan.getEndDate())
                .isActive(plan.getIsActive())
                .dietaryRestrictions(plan.getDietaryRestrictions())
                .nutritionalGoals(plan.getNutritionalGoals())
                .createdBy(plan.getCreatedBy())
                .createdAt(plan.getCreatedAt())
                .updatedAt(plan.getUpdatedAt())
                .build();
    }
}