package umc.spring.service.MissionService;

import umc.spring.domain.Mission;

import java.util.Optional;

public interface MissionQueryService {
    Optional<Mission> findMissionById(Long id);
}
