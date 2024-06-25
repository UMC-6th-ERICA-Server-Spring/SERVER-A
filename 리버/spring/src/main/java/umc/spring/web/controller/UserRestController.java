package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.UserConverter;
import umc.spring.domain.User;
import umc.spring.domain.mapping.UserMission;
import umc.spring.service.UserService.UserCommandService;
import umc.spring.validation.annotation.ExistMission;
import umc.spring.validation.annotation.ExistUser;
import umc.spring.web.dto.UserRequestDTO;
import umc.spring.web.dto.UserResponseDTO;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {

    private final UserCommandService userCommandService;

    @PostMapping("/")
    public ApiResponse<UserResponseDTO.UserJoinResultDTO> join(@RequestBody @Valid UserRequestDTO.UserJoinDTO request) {
        User user = userCommandService.joinUser(request);
        return ApiResponse.onSuccess(UserConverter.toJoinResultDTO(user));
    }

    @PostMapping("/{userId}/missions")
    public ApiResponse<UserResponseDTO.UserMissionChallengeResultDTO> challengeMission(@RequestBody @Valid UserRequestDTO.UserMissionChallengeDTO request,
                                                                                              @ExistUser @PathVariable(name = "userId") Long userId,
                                                                                              @ExistMission @RequestParam(name = "missionId") Long missionId) {
        UserMission userMission = userCommandService.challengeUserMission(userId, missionId, request);
        return ApiResponse.onSuccess(UserConverter.toChallengeResultDTO(userMission));
    }
}
