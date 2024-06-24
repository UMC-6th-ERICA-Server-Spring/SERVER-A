package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.UserMissionConverter;
import umc.spring.domain.mapping.UserMission;
import umc.spring.service.UserMissionService.UserMissionCommandService;
import umc.spring.validation.annotation.ExistMission;
import umc.spring.validation.annotation.ExistUser;
import umc.spring.web.dto.UserMissionRequestDTO;
import umc.spring.web.dto.UserMissionResponseDTO;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserMissionRestController {

    private final UserMissionCommandService userMissionCommandService;

    @PostMapping("/{userId}/missions")
    public ApiResponse<UserMissionResponseDTO.UserMissionChallengeResultDTO> challengeMission(@RequestBody @Valid UserMissionRequestDTO.UserMissionChallengeDTO request,
                                                                                              @ExistUser @PathVariable(name = "userId") Long userId,
                                                                                              @ExistMission @RequestParam(name = "missionId") Long missionId) {
        UserMission userMission = userMissionCommandService.challengeUserMission(userId, missionId, request);
        return ApiResponse.onSuccess(UserMissionConverter.toChallengeResultDTO(userMission));
    }
}
