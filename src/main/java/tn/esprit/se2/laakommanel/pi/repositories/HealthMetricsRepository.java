package tn.esprit.se2.laakommanel.pi.repositories;

import tn.esprit.se2.laakommanel.pi.entites.HealthMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HealthMetricsRepository extends JpaRepository<HealthMetrics, Long> {

    // ✅ CORRIGÉ: Find by health profile (pas patient)
    @Query("SELECT hm FROM HealthMetrics hm WHERE hm.healthProfile.id = :healthProfileId ORDER BY hm.measuredAt DESC")
    List<HealthMetrics> findByHealthProfileIdOrderByMeasuredAtDesc(@Param("healthProfileId") Long healthProfileId);

    // ✅ CORRIGÉ: Find latest metrics for a health profile
    @Query("SELECT hm FROM HealthMetrics hm WHERE hm.healthProfile.id = :healthProfileId ORDER BY hm.measuredAt DESC LIMIT 1")
    HealthMetrics findFirstByHealthProfileIdOrderByMeasuredAtDesc(@Param("healthProfileId") Long healthProfileId);

    // ✅ CORRIGÉ: Find by health profile and date range
    @Query("SELECT hm FROM HealthMetrics hm WHERE hm.healthProfile.id = :healthProfileId AND hm.measuredAt BETWEEN :startDate AND :endDate")
    List<HealthMetrics> findByHealthProfileIdAndMeasuredAtBetween(@Param("healthProfileId") Long healthProfileId,
                                                                  @Param("startDate") LocalDateTime startDate,
                                                                  @Param("endDate") LocalDateTime endDate);

    // ✅ CORRIGÉ: Find by date
    List<HealthMetrics> findByMeasuredAt(LocalDateTime date);

    // ✅ CORRIGÉ: Find by resting heart rate range
    List<HealthMetrics> findByRestingHeartRateBetween(int min, int max);

    // ✅ CORRIGÉ: Find by blood pressure (systolicBP/diastolicBP)
    @Query("SELECT hm FROM HealthMetrics hm WHERE hm.systolicBP >= :minSystolic AND hm.diastolicBP >= :minDiastolic")
    List<HealthMetrics> findByBloodPressureMin(@Param("minSystolic") int minSystolic,
                                               @Param("minDiastolic") int minDiastolic);

    // ✅ CORRIGÉ: Find abnormal readings (restingHeartRate > 100 or < 40)
    @Query("SELECT hm FROM HealthMetrics hm WHERE hm.restingHeartRate > 100 OR hm.restingHeartRate < 40")
    List<HealthMetrics> findAbnormalHeartRate();

    // ✅ CORRIGÉ: Get weight progression for a health profile
    @Query("SELECT hm.measuredAt, hm.weight FROM HealthMetrics hm WHERE hm.healthProfile.id = :healthProfileId ORDER BY hm.measuredAt")
    List<Object[]> getWeightProgression(@Param("healthProfileId") Long healthProfileId);

    // ✅ CORRIGÉ: Find metrics with significant weight change
    @Query("SELECT hm FROM HealthMetrics hm WHERE hm.healthProfile.id = :healthProfileId ORDER BY hm.measuredAt DESC")
    List<HealthMetrics> findRecentByHealthProfileId(@Param("healthProfileId") Long healthProfileId);
}