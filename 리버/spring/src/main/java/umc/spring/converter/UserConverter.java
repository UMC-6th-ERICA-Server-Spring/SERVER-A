package umc.spring.converter;

import umc.spring.domain.User;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.UserMission;
import umc.spring.web.dto.UserRequestDTO;
import umc.spring.web.dto.UserResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserConverter {

    public static UserResponseDTO.UserJoinResultDTO toJoinResultDTO(User user) {
        return UserResponseDTO.UserJoinResultDTO.builder()
                .userId(user.getId())
                .createAt(LocalDateTime.now())
                .build();
    }

    public static User toUser(UserRequestDTO.UserJoinDTO request) {

        Gender gender = switch (request.getGender()) {
            case 1 -> Gender.MALE;
            case 2 -> Gender.FEMALE;
            case 3 -> Gender.NONE;
            default -> null;
        };

        return User.builder()
                .name(request.getName())
                .gender(gender)
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .phoneNumber(request.getPhoneNumber())
                .birthYear(request.getBirthYear())
                .birthMonth(request.getBirthMonth())
                .birthDay(request.getBirthDay())
                .userFoodList(new ArrayList<>())
                .build();
    }

    public static UserResponseDTO.UserMissionChallengeResultDTO toChallengeResultDTO(UserMission userMission) {
        return UserResponseDTO.UserMissionChallengeResultDTO.builder()
                .userMissionId(userMission.getId())
                .createAt(LocalDateTime.now())
                .build();
    }

    public static UserMission toUserMission(UserRequestDTO.UserMissionChallengeDTO request) {
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
