package umc.spring.service.StoreService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.UserReview;

import java.util.Optional;

public interface StoreQueryService {
    Optional<Store> findStoreById(Long id);

    Optional<Mission> findMissionById(Long id);

    Page<UserReview> getUserReviewList(Long StoreId, Integer page);

    Page<Mission> getMissionList(Long storeId, Integer page);
}
