package umc.spring.service.UserReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.UserReviewConverter;
import umc.spring.domain.mapping.UserReview;
import umc.spring.repository.StoreRepository;
import umc.spring.repository.UserRepository;
import umc.spring.repository.UserReviewRepository;
import umc.spring.web.dto.UserReviewRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class UserReviewCommandServiceImpl implements UserReviewCommandService {

    private final UserReviewRepository userReviewRepository;

    private final UserRepository userRepository;

    private final StoreRepository storeRepository;

    @Override
    public UserReview createUserReview(Long userId, Long storeId, UserReviewRequestDTO.UserReviewCreateDTO request) {
        UserReview newUserReview = UserReviewConverter.toUserReview(request);

        newUserReview.setUser(userRepository.findById(userId).get());
        newUserReview.setStore(storeRepository.findById(storeId).get());

        return userReviewRepository.save(newUserReview);
    }
}
