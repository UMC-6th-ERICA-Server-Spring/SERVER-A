package umc.spring.converter;

import umc.spring.domain.User;
import umc.spring.domain.mapping.UserReview;
import umc.spring.web.dto.UserReviewRequestDTO;
import umc.spring.web.dto.UserReviewResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserReviewConverter {

    public static UserReviewResponseDTO.UserReviewCreateResultDTO toCreateResultDTO(UserReview userReview) {
        return UserReviewResponseDTO.UserReviewCreateResultDTO.builder()
                .reviewId(userReview.getId())
                .createAt(LocalDateTime.now())
                .build();
    }

    public static UserReview toUserReview(UserReviewRequestDTO.UserReviewCreateDTO request) {
        return UserReview.builder()
                .star(request.getStar())
                .body(request.getBody())
                //.reviewImages(new ArrayList<>())
                .build();
    }
}
