package umc.spring.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.User;
import umc.spring.domain.mapping.UserMission;
import umc.spring.domain.mapping.UserReview;
import umc.spring.repository.FoodRepository;
import umc.spring.repository.UserMissionRepository;
import umc.spring.repository.UserRepository;
import umc.spring.repository.UserReviewRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    private final FoodRepository foodRepository;

    private final UserMissionRepository userMissionRepository;

    private final UserReviewRepository userReviewRepository;

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean FoodValid(List<Long> foods) {
        boolean isValid = foods.stream()
                .allMatch(food -> foodRepository.existsById(food));
        return isValid;
    }

    @Override
    public Optional<UserMission> findUserMissionById(Long userMissionId) {
        return userMissionRepository.findById(userMissionId);
    }

    @Override
    public Page<UserReview> getMyReviewList(Long userId, Integer page) {

        User user = userRepository.findById(userId).get();

        Page<UserReview> userReviewPage = userReviewRepository.findAllByUser(user, PageRequest.of(page, 10));
        return userReviewPage;
    }

    @Override
    public Page<UserMission> getMyMissionList(Long userId, Integer page) {

        User user = userRepository.findById(userId).get();

        Page<UserMission> userMissionPage = userMissionRepository.findAllByUser(user, PageRequest.of(page, 10));
        return userMissionPage;
    }
}
