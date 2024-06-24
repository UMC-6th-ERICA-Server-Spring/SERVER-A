package umc.spring.converter;

import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.UserMission;
import umc.spring.web.dto.UserMissionRequestDTO;
import umc.spring.web.dto.UserMissionResponseDTO;

import java.time.LocalDateTime;

public class UserMissionConverter {

    public static UserMissionResponseDTO.UserMissionChallengeResultDTO toChallengeResultDTO(UserMission userMission) {
        return UserMissionResponseDTO.UserMissionChallengeResultDTO.builder()
                .userMissionId(userMission.getId())
                .createAt(LocalDateTime.now())
                .build();
    }

    public static UserMission toUserMission(UserMissionRequestDTO.UserMissionChallengeDTO request) {
        if (request.getStatus() == 0) {
            return UserMission.builder()
                    .status(MissionStatus.ONGOING)
                    .build();
        }
        else {
            return UserMission.builder()
                    .status(MissionStatus.COMPLETE)
                    .build();
        }
    }
}
