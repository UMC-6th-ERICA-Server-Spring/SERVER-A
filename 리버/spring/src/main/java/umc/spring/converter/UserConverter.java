package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.User;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.UserMission;
import umc.spring.domain.mapping.UserReview;
import umc.spring.web.dto.StoreResponseDTO;
import umc.spring.web.dto.UserRequestDTO;
import umc.spring.web.dto.UserResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static UserResponseDTO.MyMissionPreviewDTO myMissionPreviewDTO(UserMission userMission) {
        return UserResponseDTO.MyMissionPreviewDTO.builder()
                .storeNickname(userMission.getMission().getStore().getName())
                .point(userMission.getMission().getPoint())
                .price(userMission.getMission().getPrice())
                .status(userMission.getStatus().toString())
                .build();
    }

    public static UserResponseDTO.MyMissionPreviewListDTO myMIssionPreviewListDTO(Page<UserMission> userMissionList) {

        List<UserResponseDTO.MyMissionPreviewDTO> myMissionPreviewDTOList = userMissionList.stream()
                .map(UserConverter::myMissionPreviewDTO).collect(Collectors.toList());

        return UserResponseDTO.MyMissionPreviewListDTO.builder()
                .missionList(myMissionPreviewDTOList)
                .listSize(myMissionPreviewDTOList.size())
                .totalPage(userMissionList.getTotalPages())
                .totalElements(userMissionList.getTotalElements())
                .isFirst(userMissionList.isFirst())
                .isLast(userMissionList.isLast())
                .build();
    }

    public static UserResponseDTO.MyReviewPreviewDTO myReviewPreviewDTO(UserReview userReview) {
        return UserResponseDTO.MyReviewPreviewDTO.builder()
                .ownerNickname(userReview.getUser().getName())
                .storeNickname(userReview.getStore().getName())
                .star(userReview.getStar())
                .body(userReview.getBody())
                .createAt(userReview.getCreatedAt().toLocalDate())
                .build();
    }

    public static UserResponseDTO.MyReviewPreviewListDTO myReviewPreviewListDTO(Page<UserReview> userReviewList) {

        List<UserResponseDTO.MyReviewPreviewDTO> myReviewPreviewDTOList = userReviewList.stream()
                .map(UserConverter::myReviewPreviewDTO).collect(Collectors.toList());

        return UserResponseDTO.MyReviewPreviewListDTO.builder()
                .reviewList(myReviewPreviewDTOList)
                .listSize(myReviewPreviewDTOList.size())
                .totalPage(userReviewList.getTotalPages())
                .totalElements(userReviewList.getTotalElements())
                .isFirst(userReviewList.isFirst())
                .isLast(userReviewList.isLast())
                .build();
    }
}
