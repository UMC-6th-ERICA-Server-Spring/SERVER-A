package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.UserReview;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.repository.UserRepository;
import umc.spring.repository.UserReviewRepository;
import umc.spring.web.dto.StoreRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreCommandServiceImpl implements StoreCommandService {

    private final UserReviewRepository userReviewRepository;

    private final UserRepository userRepository;

    private final StoreRepository storeRepository;

    private final MissionRepository missionRepository;

    @Override
    @Transactional
    public Store joinStore(StoreRequestDTO.StoreJoinDTO request) {

        Store newStore = StoreConverter.toStore(request);

        return storeRepository.save(newStore);
    }

    @Override
    @Transactional
    public UserReview createUserReview(Long userId, Long storeId, StoreRequestDTO.UserReviewCreateDTO request) {
        UserReview newUserReview = StoreConverter.toUserReview(request);

        newUserReview.setUser(userRepository.findById(userId).get());
        newUserReview.setStore(storeRepository.findById(storeId).get());

        return userReviewRepository.save(newUserReview);
    }

    @Override
    @Transactional
    public Mission createMission(Long storeId, StoreRequestDTO.MissionCreateDTO request) {

        Mission mission = StoreConverter.toMission(request);

        mission.setStore(storeRepository.findById(storeId).get());

        return missionRepository.save(mission);
    }
}
