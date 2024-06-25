package umc.spring.service.UserService;

import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.User;
import umc.spring.domain.mapping.UserMission;
import umc.spring.web.dto.UserRequestDTO;

public interface UserCommandService {
    User joinUser(UserRequestDTO.UserJoinDTO request);

    @Transactional
    UserMission challengeUserMission(Long userId, Long missionId, UserRequestDTO.UserMissionChallengeDTO request);
}
