package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Mission;
import umc.spring.repository.MissionRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;

    @Override
    public Optional<Mission> findMissionById(Long id) {
        return missionRepository.findById(id);
    }
}
