package umc.spring.service.UserService;

import org.springframework.data.domain.Page;
import umc.spring.domain.User;
import umc.spring.domain.mapping.UserMission;
import umc.spring.domain.mapping.UserReview;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    Optional<User> findUserById(Long id);

    boolean FoodValid(List<Long> foods);

    Optional<UserMission> findUserMissionById(Long userMissionId);

    Page<UserReview> getMyReviewList(Long userId, Integer page);

    Page<UserMission> getMyMissionList(Long userId, Integer page);
}
