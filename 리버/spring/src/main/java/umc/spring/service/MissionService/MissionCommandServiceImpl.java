package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.MissionRequesetDTO;

@Service
@Transactional
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;

    private final StoreRepository storeRepository;

    @Override
    public Mission createMission(Long storeId, MissionRequesetDTO.MissionCreateDTO request) {

        Mission mission = MissionConverter.toMission(request);

        mission.setStore(storeRepository.findById(storeId).get());

        return missionRepository.save(mission);
    }
}
