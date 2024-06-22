package umc.spring.service.UserReviewService;

import umc.spring.domain.mapping.UserReview;
import umc.spring.web.dto.UserReviewRequestDTO;

public interface UserReviewCommandService {
    UserReview createUserReview(Long userId, Long storeId, UserReviewRequestDTO.UserReviewCreateDTO request);
}
