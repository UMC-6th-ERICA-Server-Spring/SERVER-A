package umc.spring.service.UserMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.UserMissionHandler;
import umc.spring.converter.UserMissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.User;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.UserMission;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.UserMissionRepository;
import umc.spring.repository.UserRepository;
import umc.spring.validation.annotation.IfOngoing;
import umc.spring.web.dto.UserMissionRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class UserMissionCommandServiceImpl implements UserMissionCommandService {

    private final UserMissionRepository userMissionRepository;

    private final UserRepository userRepository;

    private final MissionRepository missionRepository;

    //@IfOngoing(missionStatus = "missionStatus")
    @Override
    public UserMission challengeUserMission(Long userId, Long missionId, UserMissionRequestDTO.UserMissionChallengeDTO request) {

        Mission mission = missionRepository.findById(missionId).get();
        User user = userRepository.findById(userId).get();
        UserMission userMission = userMissionRepository.findByMissionAndUser(mission, user);

        if (userMission != null) {
            //String missionStatus = userMission.getStatus().toString();
            if (userMission.getStatus()== MissionStatus.ONGOING) {
                throw new UserMissionHandler(ErrorStatus.MISSION_ONGOING);
            }
        }

        UserMission newUserMission = UserMissionConverter.toUserMission(request);

        newUserMission.setUser(userRepository.findById(userId).get());
        newUserMission.setMission(missionRepository.findById(missionId).get());

        return userMissionRepository.save(newUserMission);
    }
}
