package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.UserConverter;
import umc.spring.domain.User;
import umc.spring.domain.mapping.UserMission;
import umc.spring.service.UserService.UserCommandService;
import umc.spring.service.UserService.UserQueryService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistMission;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.validation.annotation.ExistUser;
import umc.spring.web.dto.UserRequestDTO;
import umc.spring.web.dto.UserResponseDTO;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {

    private final UserCommandService userCommandService;

    private final UserQueryService userQueryService;

    @PostMapping("/")
    @Operation(summary = "회원가입 API", description = "회원가입 API 입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH0003", description = "access token을 주세요", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess token 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess token 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<UserResponseDTO.UserJoinResultDTO> join(@RequestBody @Valid UserRequestDTO.UserJoinDTO request) {
        User user = userCommandService.joinUser(request);
        return ApiResponse.onSuccess(UserConverter.toJoinResultDTO(user));
    }

    @PostMapping("/{userId}/missions")
    @Operation(summary = "미션 도전하기 API", description = "가게의 미션을 도전 중인 내 미션에 추가하는 API 입니다. 도전할 미션의 missionID와 도전하는 사용자의 userId가 필요합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH0003", description = "access token을 주세요", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess token 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess token 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "사용자의 아이디, path variable 입니다."),
            @Parameter(name = "missionId", description = "미션의 아이디, 도전할 미션의 아이디 입니다.")
    })

    

    public ApiResponse<UserResponseDTO.UserMissionChallengeResultDTO> challengeMission(@RequestBody @Valid UserRequestDTO.UserMissionChallengeDTO request,
                                                                                              @ExistUser @PathVariable(name = "userId") Long userId,
                                                                                              @ExistMission @RequestParam(name = "missionId") Long missionId) {
        UserMission userMission = userCommandService.challengeUserMission(userId, missionId, request);
        return ApiResponse.onSuccess(UserConverter.toChallengeResultDTO(userMission));
    }

    @GetMapping("/{userId}/missions")
    @Operation(summary = "내가 진행 중인 미션 목록 조회 API", description = "내가 진행 중인 미션 목록을 조회하는 API이며, 페이징을 포함합니다. queryString으로 page 번호를 주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH0003", description = "access token을 주세요", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess token 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess token 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "사용자의 아이디, path variable 입니다."),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다.")
    })
    public ApiResponse<UserResponseDTO.MyMissionPreviewDTO> getMyMissionList(@ExistUser @PathVariable(name = "userId") Long userId,
                                                                             @CheckPage @RequestParam(name = "page") Integer page) {
        userQueryService.getMyMissionList(userId, page);
        return null;
    }

    @GetMapping("/{userId}/reviews")
    @Operation(summary = "내가 작성한 리뷰 목록 API", description = "내가 작성한 리뷰 목록을 조회하는 API이며, 페이징을 포함합니다. queryString으로 page 번호와 사용자의 아이디를 주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH0003", description = "access token을 주세요", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess token 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess token 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "사용자의 아이디, path variable 입니다."),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다.")
    })
    public ApiResponse<UserResponseDTO.MyReviewPreviewDTO> getMyReviewList(@ExistStore @PathVariable(name = "userId") Long userId,
                                                                           @CheckPage @RequestParam(name = "page") Integer page) {
        userQueryService.getMyReviewList(userId,page);
        return null;
    }
}
