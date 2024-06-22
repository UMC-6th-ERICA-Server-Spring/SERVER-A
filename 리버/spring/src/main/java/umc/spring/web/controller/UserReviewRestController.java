package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.UserReviewConverter;
import umc.spring.domain.mapping.UserReview;
import umc.spring.service.UserReviewService.UserReviewCommandService;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.validation.annotation.ExistUser;
import umc.spring.web.dto.UserReviewRequestDTO;
import umc.spring.web.dto.UserReviewResponseDTO;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/stores")
public class UserReviewRestController {

    private final UserReviewCommandService userReviewCommandService;

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<UserReviewResponseDTO.UserReviewCreateResultDTO> createReview(@RequestBody @Valid UserReviewRequestDTO.UserReviewCreateDTO request,
                                                                                     @ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                                     @ExistUser @RequestParam(name = "userId") Long userId) {
        UserReview userReview = userReviewCommandService.createUserReview(userId, storeId, request);
        return ApiResponse.onSuccess(UserReviewConverter.toCreateResultDTO(userReview));
    }
}
