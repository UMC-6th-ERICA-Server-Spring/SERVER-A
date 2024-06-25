package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;

    private final MissionRepository missionRepository;

    @Override
    public Optional<Store> findStoreById(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public Optional<Mission> findMissionById(Long id) {
        return missionRepository.findById(id);
    }
}
