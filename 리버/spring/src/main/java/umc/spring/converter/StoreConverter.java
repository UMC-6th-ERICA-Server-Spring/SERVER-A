package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.UserReview;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    public static Store toStore(StoreRequestDTO.StoreJoinDTO request) {
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .region(request.getRegion())
                .userReviewList(new ArrayList<>())
                .missionList(new ArrayList<>())
                .build();
    }

    public static StoreResponseDTO.StoreJoinResultDTO toJoinResultDTO(Store store) {
        return StoreResponseDTO.StoreJoinResultDTO.builder()
                .storeId(store.getId())
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

    public static StoreResponseDTO.UserReviewCreateResultDTO toCreateResultDTO(UserReview userReview) {
        return StoreResponseDTO.UserReviewCreateResultDTO.builder()
                .reviewId(userReview.getId())
                .createAt(LocalDateTime.now())
                .build();
    }

    public static StoreResponseDTO.UserReviewPreviewDTO userReviewPreviewDTO(UserReview userReview) {
        return StoreResponseDTO.UserReviewPreviewDTO.builder()
                .ownerNickname(userReview.getUser().getName())
                .star(userReview.getStar())
                .body(userReview.getBody())
                .createAt(userReview.getCreatedAt().toLocalDate())
                .build();
    }

    public static StoreResponseDTO.UserReviewPreviewListDTO userReviewPreviewListDTO(Page<UserReview> userReviewList) {

        List<StoreResponseDTO.UserReviewPreviewDTO> userReviewPreviewDTOList = userReviewList.stream()
                .map(StoreConverter::userReviewPreviewDTO).collect(Collectors.toList());

        return StoreResponseDTO.UserReviewPreviewListDTO.builder()
                .reviewList(userReviewPreviewDTOList)
                .listSize(userReviewPreviewDTOList.size())
                .totalPage(userReviewList.getTotalPages())
                .totalElements(userReviewList.getTotalElements())
                .isFirst(userReviewList.isFirst())
                .isLast(userReviewList.isLast())
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

    public static StoreResponseDTO.MissionPreviewDTO MissionPreviewDTO(Mission mission) {
        return StoreResponseDTO.MissionPreviewDTO.builder()
                .storeNickname(mission.getStore().getName())
                .address(mission.getStore().getAddress())
                .price(mission.getPrice())
                .point(mission.getPoint())
                .build();
    }

    public static StoreResponseDTO.MissionPreviewListDTO MissionPreviewListDTO(Page<Mission> missionList) {

        List<StoreResponseDTO.MissionPreviewDTO> missionPreviewDTOList = missionList.stream()
                .map(StoreConverter::MissionPreviewDTO).collect(Collectors.toList());

        return StoreResponseDTO.MissionPreviewListDTO.builder()
                .missionList(missionPreviewDTOList)
                .listSize(missionPreviewDTOList.size())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .isFirst(missionList.isFirst())
                .isLast(missionList.isLast())
                .build();
    }
}
