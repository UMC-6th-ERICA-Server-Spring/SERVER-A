package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.UserReview;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.validation.annotation.ExistUser;
import umc.spring.web.dto.*;
import umc.spring.service.StoreService.StoreCommandService;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    @PostMapping("/")
    public ApiResponse<StoreResponseDTO.StoreJoinResultDTO> join(@RequestBody @Valid StoreRequestDTO.StoreJoinDTO request) {
        Store store = storeCommandService.joinStore(request);
        return ApiResponse.onSuccess(StoreConverter.toJoinResultDTO(store));
    }

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<StoreResponseDTO.UserReviewCreateResultDTO> createReview(@RequestBody @Valid StoreRequestDTO.UserReviewCreateDTO request,
                                                                                     @ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                                     @ExistUser @RequestParam(name = "userId") Long userId) {
        UserReview userReview = storeCommandService.createUserReview(userId, storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toCreateResultDTO(userReview));
    }

    @PostMapping("/{storeId}/missions")
    public ApiResponse<StoreResponseDTO.MissionCreateResultDTO> createMission(@RequestBody @Valid StoreRequestDTO.MissionCreateDTO request,
                                                                                @ExistStore @PathVariable Long storeId) {
        Mission mission = storeCommandService.createMission(storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toCreateMissionResultDTO(mission));
    }
}
