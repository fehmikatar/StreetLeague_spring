package tn.esprit.se2.laakommanel.pi.services;

import tn.esprit.se2.laakommanel.pi.entites.HealthProfile;
import tn.esprit.se2.laakommanel.pi.entites.FitnessStatus;
import java.util.List;

public interface IHealthProfileService {
    List<HealthProfile> getAllHealthProfiles();
    HealthProfile getHealthProfileById(Long id);
    HealthProfile createHealthProfile(HealthProfile healthProfile);
    HealthProfile updateHealthProfile(Long id, HealthProfile healthProfile);
    void deleteHealthProfile(Long id);
    HealthProfile getHealthProfileByUser(Long userId);
    List<HealthProfile> getHealthProfilesByFitnessStatus(FitnessStatus status);
}