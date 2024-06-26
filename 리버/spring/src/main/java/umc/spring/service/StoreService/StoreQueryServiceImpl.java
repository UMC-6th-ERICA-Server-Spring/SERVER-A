package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.UserReview;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.repository.UserReviewRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;

    private final MissionRepository missionRepository;

    private final UserReviewRepository userReviewRepository;

    @Override
    public Optional<Store> findStoreById(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public Optional<Mission> findMissionById(Long id) {
        return missionRepository.findById(id);
    }

    @Override
    public Page<UserReview> getUserReviewList(Long StoreId, Integer page) {

        Store store = storeRepository.findById(StoreId).get();

        Page<UserReview> storeReviewPage = userReviewRepository.findAllByStore(store, PageRequest.of(page, 10));
        return storeReviewPage;
    }

    @Override
    public Page<Mission> getMissionList(Long storeId, Integer page) {

        Store store = storeRepository.findById(storeId).get();

        Page<Mission> missionPage = missionRepository.findAllByStore(store, PageRequest.of(page, 10));
        return missionPage;
    }
}
