package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.MissionRequesetDTO;
import umc.spring.web.dto.MissionResponseDTO;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/stores")
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/{storeId}/missions")
    public ApiResponse<MissionResponseDTO.MissionCreateResultDTO> createMission(@RequestBody @Valid MissionRequesetDTO.MissionCreateDTO request,
                                                                                @ExistStore @PathVariable Long storeId) {
        Mission mission = missionCommandService.createMission(storeId, request);
        return ApiResponse.onSuccess(MissionConverter.toCreateMissionResultDTO(mission));
    }
}
