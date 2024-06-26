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
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.UserReview;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.validation.annotation.ExistUser;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.web.dto.*;
import umc.spring.service.StoreService.StoreCommandService;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    private final StoreQueryService storeQueryService;

    @PostMapping("/")
    @Operation(summary = "특정 지역에 가게 추가 API", description = "특정 지역에 가게를 추가하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH0003", description = "access token을 주세요", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess token 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess token 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<StoreResponseDTO.StoreJoinResultDTO> join(@RequestBody @Valid StoreRequestDTO.StoreJoinDTO request) {
        Store store = storeCommandService.joinStore(request);
        return ApiResponse.onSuccess(StoreConverter.toJoinResultDTO(store));
    }

    @PostMapping("/{storeId}/reviews")
    @Operation(summary = "가게 리뷰 추가 API", description = "가게에 리뷰를 추가하는 API입니다. 리뷰를 쓸 가게의 storeId와 리뷰를 쓰는 사용자의 userId가 필요합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH0003", description = "access token을 주세요", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess token 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess token 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다."),
            @Parameter(name = "userId", description = "사용자의 아이디, 리뷰를 쓰는 사용자의 아이디 입니다."),
    })
    public ApiResponse<StoreResponseDTO.UserReviewCreateResultDTO> createReview(@RequestBody @Valid StoreRequestDTO.UserReviewCreateDTO request,
                                                                                     @ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                                     @ExistUser @RequestParam(name = "userId") Long userId) {
        UserReview userReview = storeCommandService.createUserReview(userId, storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toCreateResultDTO(userReview));
    }

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가게의 리뷰 목록을 조회하는 API이며, 페이징을 포함합니다. queryString으로 page 번호를 주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH0003", description = "access token을 주세요", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess token 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess token 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다."),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<StoreResponseDTO.UserReviewPreviewListDTO> getUserReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                                    @CheckPage @RequestParam(name = "page") Integer page) {
        storeQueryService.getUserReviewList(storeId, page);
        return null;
    }

    @PostMapping("/{storeId}/missions")
    @Operation(summary = "가게 미션 추가 API", description = "가게에 미션을 추가하는 API 입니다. 미션을 추가할 가게의 storeId가 필요합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH0003", description = "access token을 주세요", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess token 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess token 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다."),
    })
    public ApiResponse<StoreResponseDTO.MissionCreateResultDTO> createMission(@RequestBody @Valid StoreRequestDTO.MissionCreateDTO request,
                                                                                @ExistStore @PathVariable Long storeId) {
        Mission mission = storeCommandService.createMission(storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toCreateMissionResultDTO(mission));
    }

    @GetMapping("/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게의 미션 목록을 조회하는 API이며, 페이징을 포함합니다. queryString으로 page 번호를 주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH0003", description = "access token을 주세요", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess token 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess token 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 이이디, path variable 입니다."),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<StoreResponseDTO.MissionPreviewDTO> getMissionList(@ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                          @CheckPage @RequestParam(name = "page") Integer page) {
        storeQueryService.getMissionList(storeId, page);
        return null;
    }
}
