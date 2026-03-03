package tn.esprit._4se2.laakommanel.pi.mapper;

import org.mapstruct.*;
import tn.esprit._4se2.laakommanel.pi.dto.HealthMetricsRequest;
import tn.esprit._4se2.laakommanel.pi.dto.HealthMetricsResponse;
import tn.esprit._4se2.laakommanel.pi.entites.HealthMetrics;
import tn.esprit._4se2.laakommanel.pi.entites.HealthProfile;

@Mapper(componentModel = "spring", uses = {HealthProfileMapperMapStruct.class})
public interface HealthMetricsMapperMapStruct {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "healthProfileId", target = "healthProfile.id")
    @Mapping(target = "measuredAt", ignore = true)
    HealthMetrics toEntity(HealthMetricsRequest request);

    @Mapping(source = "healthProfile.id", target = "healthProfileId")
    HealthMetricsResponse toResponse(HealthMetrics metrics);

    default HealthProfile mapHealthProfileIdToHealthProfile(Long healthProfileId) {
        if (healthProfileId == null) return null;
        HealthProfile profile = new HealthProfile();
        profile.setId(healthProfileId);
        return profile;
    }
}