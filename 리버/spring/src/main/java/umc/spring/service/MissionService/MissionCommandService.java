package umc.spring.service.MissionService;

import umc.spring.domain.Mission;
import umc.spring.web.dto.MissionRequesetDTO;

public interface MissionCommandService {
    Mission createMission(Long storeId, MissionRequesetDTO.MissionCreateDTO request);
}
