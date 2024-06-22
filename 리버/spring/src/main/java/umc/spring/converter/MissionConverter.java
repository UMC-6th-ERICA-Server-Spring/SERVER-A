package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.web.dto.MissionRequesetDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;

public class MissionConverter {

    public static Mission toMission(MissionRequesetDTO.MissionCreateDTO request) {
        return Mission.builder()
                .price(request.getPrice())
                .point(request.getPoint())
                .deadline(request.getDeadline())
                .build();
    }

    public static MissionResponseDTO.MissionCreateResultDTO toCreateMissionResultDTO(Mission mission) {
        return MissionResponseDTO.MissionCreateResultDTO.builder()
                .missionId(mission.getId())
                .createAt(LocalDateTime.now())
                .build();
    }
}
