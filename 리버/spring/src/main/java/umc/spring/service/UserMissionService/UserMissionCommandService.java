package umc.spring.service.UserMissionService;

import umc.spring.domain.mapping.UserMission;
import umc.spring.web.dto.UserMissionRequestDTO;

public interface UserMissionCommandService {

    UserMission challengeUserMission(Long userId, Long missionId, UserMissionRequestDTO.UserMissionChallengeDTO request);
}
