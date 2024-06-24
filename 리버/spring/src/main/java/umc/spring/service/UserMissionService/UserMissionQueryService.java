package umc.spring.service.UserMissionService;

import umc.spring.domain.mapping.UserMission;

import java.util.Optional;

public interface UserMissionQueryService {
    Optional<UserMission> findUserMissionById(Long userMissionId);
}
