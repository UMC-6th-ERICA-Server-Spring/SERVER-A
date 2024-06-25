package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.UserReview;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class StoreConverter {

    public static StoreResponseDTO.StoreJoinResultDTO toJoinResultDTO(Store store) {
        return StoreResponseDTO.StoreJoinResultDTO.builder()
                .storeId(store.getId())
                .createAt(LocalDateTime.now())
                .build();
    }

    public static Store toStore(StoreRequestDTO.StoreJoinDTO request) {
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .region(request.getRegion())
                .userReviewList(new ArrayList<>())
                .missionList(new ArrayList<>())
                .build();
    }

    public static StoreResponseDTO.UserReviewCreateResultDTO toCreateResultDTO(UserReview userReview) {
        return StoreResponseDTO.UserReviewCreateResultDTO.builder()
                .reviewId(userReview.getId())
                .createAt(LocalDateTime.now())
                .build();
    }

    public static UserReview toUserReview(StoreRequestDTO.UserReviewCreateDTO request) {
        return UserReview.builder()
                .star(request.getStar())
                .body(request.getBody())
                //.reviewImages(new ArrayList<>())
                .build();
    }

    public static Mission toMission(StoreRequestDTO.MissionCreateDTO request) {
        return Mission.builder()
                .price(request.getPrice())
                .point(request.getPoint())
                .deadline(request.getDeadline())
                .build();
    }

    public static StoreResponseDTO.MissionCreateResultDTO toCreateMissionResultDTO(Mission mission) {
        return StoreResponseDTO.MissionCreateResultDTO.builder()
                .missionId(mission.getId())
                .createAt(LocalDateTime.now())
                .build();
    }
}
