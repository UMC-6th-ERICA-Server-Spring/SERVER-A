package umc.spring.service.StoreService;

import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.UserReview;
import umc.spring.web.dto.StoreRequestDTO;

public interface StoreCommandService {
    @Transactional
    Store joinStore(StoreRequestDTO.StoreJoinDTO request);

    @Transactional
    UserReview createUserReview(Long userId, Long storeId, StoreRequestDTO.UserReviewCreateDTO request);

    @Transactional
    Mission createMission(Long storeId, StoreRequestDTO.MissionCreateDTO request);
}
