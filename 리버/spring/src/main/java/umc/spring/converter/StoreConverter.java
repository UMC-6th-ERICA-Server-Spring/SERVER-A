package umc.spring.converter;

import umc.spring.domain.Store;
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
}
